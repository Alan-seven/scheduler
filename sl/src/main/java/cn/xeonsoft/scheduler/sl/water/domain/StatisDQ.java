package cn.xeonsoft.scheduler.sl.water.domain;

import java.util.Date;

/**
 * 流量日统计
 */
public class StatisDQ {
    private String stcd;
    private Date tm;
    private Float avq;
    private Float ehq;
    private Float htq;
    private Float ltq;
    private Date htqtm;
    private Date ltqtm;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public Float getAvq() {
        return avq;
    }

    public void setAvq(Float avq) {
        this.avq = avq;
    }

    public Float getEhq() {
        return ehq;
    }

    public void setEhq(Float ehq) {
        this.ehq = ehq;
    }

    public Float getHtq() {
        return htq;
    }

    public void setHtq(Float htq) {
        this.htq = htq;
    }

    public Float getLtq() {
        return ltq;
    }

    public void setLtq(Float ltq) {
        this.ltq = ltq;
    }

    public Date getHtqtm() {
        return htqtm;
    }

    public void setHtqtm(Date htqtm) {
        this.htqtm = htqtm;
    }

    public Date getLtqtm() {
        return ltqtm;
    }

    public void setLtqtm(Date ltqtm) {
        this.ltqtm = ltqtm;
    }
}
