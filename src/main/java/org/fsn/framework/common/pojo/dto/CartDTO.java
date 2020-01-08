package org.fsn.framework.common.pojo.dto;

public class CartDTO {

    private Long uid; //用户id
    private Long pid; //产品id
    private Long gid; //货品id
    private Long prmid; //规格id
    private Integer an;  //活动编号
    private Long ct;// 创建时间
    private Integer count;//数量

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getPrmid() {
        return prmid;
    }

    public void setPrmid(Long prmid) {
        this.prmid = prmid;
    }

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
