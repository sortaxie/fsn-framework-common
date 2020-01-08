package org.fsn.framework.common.pojo.dto;

public class CartDTO {

    private long uid; //用户id
    private long pid; //产品id
    private long gid; //货品id
    private long prmid; //规格id
    private int an;  //活动编号
    private long ct;// 创建时间

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public long getPrmid() {
        return prmid;
    }

    public void setPrmid(long prmid) {
        this.prmid = prmid;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public long getCt() {
        return ct;
    }

    public void setCt(long ct) {
        this.ct = ct;
    }
}
