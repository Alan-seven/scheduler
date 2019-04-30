package cn.xeonsoft.scheduler.sl.water.bo;

import java.util.Date;

public class Extreme {
    private String stcd;    //测站编码
    private Date extremumTm;    //极值对应时间
    private Float extremum;     //极值
    private Date tm;    //水情发生时间

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public Date getExtremumTm() {
        return extremumTm;
    }

    public void setExtremumTm(Date extremumTm) {
        this.extremumTm = extremumTm;
    }

    public Float getExtremum() {
        return extremum;
    }

    public void setExtremum(Float extremum) {
        this.extremum = extremum;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }
}
