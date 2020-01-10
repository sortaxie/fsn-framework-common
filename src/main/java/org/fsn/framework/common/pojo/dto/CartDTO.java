package org.fsn.framework.common.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {
    @JsonIgnore
    public  static String  DEFALUTP ="shop";
    private String p;//项目名称
    private Long uid; //用户id
    private Long pid; //产品id
    private Long gid; //货品id
    private Long prmid; //规格id
    private Integer an;  //活动编号
    private Long ct;// 创建时间
    private Integer count;//数量
    private Integer cc;//  增加减少数量使用,添加传正数，减少传负数

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

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

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }
}
