package cn.xeonsoft.scheduler.erhai.run.bo;

import org.apache.commons.lang3.StringUtils;

public class InW {
    private Float flow; //净入湖
    private Float w;    //净入水量
    private String tm;
    private Integer yr;
    private Integer mnth;

    public Float getFlow() {
        return flow;
    }

    public void setFlow(Float flow) {
        this.flow = flow;
    }

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Integer getYr() {
        return yr;
    }

    public void setYr(Integer yr) {
        this.yr = yr;
    }

    public Integer getMnth() {
        return mnth;
    }

    public void setMnth(Integer mnth) {
        this.mnth = mnth;
    }

    public String getTm() {
        if(StringUtils.isNotBlank(tm)){
            return tm;
        }
        return yr+"-"+mnth + "-01 00:00:00";
    }

    public void setTm(String tm) {
        this.tm = tm;
    }
}
