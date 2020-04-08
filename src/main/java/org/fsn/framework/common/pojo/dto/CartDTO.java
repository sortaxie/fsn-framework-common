package org.fsn.framework.common.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {
    @JsonIgnore
    public  static String  DEFALUTP ="shop";
    private String p;//项目名称
    private String uid; //用户id
    private Long pid; //产品id
    private Long skuid; //货品id
    private Long specid; //规格id
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSkuid() {
        return skuid;
    }

    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    public Long getSpecid() {
        return specid;
    }

    public void setSpecid(Long specid) {
        this.specid = specid;
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
