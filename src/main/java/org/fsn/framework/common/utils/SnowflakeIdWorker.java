package org.fsn.framework.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: SnowflakeIdWorker
 * @Description: 分布式id
 * @author: 熊俊堡
 * @Date: 2019/05/21 14:25
 * @Version: 1.0
 */
public class SnowflakeIdWorker {

    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1489111610226L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;


    private volatile static SnowflakeIdWorker snowflakeIdWorker = null;

    private SnowflakeIdWorker() {
    }


    public static SnowflakeIdWorker getInstance(long workerId, long dataCenterId) {
        if (snowflakeIdWorker == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (snowflakeIdWorker == null) {
                    snowflakeIdWorker = new SnowflakeIdWorker(workerId, dataCenterId);
                }
            }
        }
        return snowflakeIdWorker;
    }

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    private SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized String nextId() {
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return Long.toString(((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {

        final Map<String, String> map = new HashMap<String, String>();
        final String[] error = {"no"};

        Thread t1 = new Thread() {
            public void run() {
                SnowflakeIdWorker idWorker = SnowflakeIdWorker.getInstance(1, 1);
                for (int i = 0; i < 10000; i++) {
                    String id = idWorker.nextId();
                    if (map.containsKey(id)) {
                        error[0] = "has";
                        break;
                    }
                    map.put(idWorker.nextId(), "1");
                    System.out.println(Thread.currentThread().getId()+":"+id);
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                SnowflakeIdWorker idWorker = SnowflakeIdWorker.getInstance(1, 1);
                for (int i = 0; i < 10000; i++) {

                    String id = idWorker.nextId();
                    if (map.containsKey(id)) {
                        error[0] = "has";
                        break;
                    }
                    map.put(idWorker.nextId(), "1");
                    System.out.println(Thread.currentThread().getId()+":"+id);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(error[0]);

    }

}
