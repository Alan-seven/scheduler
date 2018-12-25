package cn.xeonsoft.scheduler.sl.water.domain;

import java.util.Date;

/**
 * 水位日统计
 */
public class StatisDZ {
    private String stcd;
    private Date tm;
    private Float avz;
    private Float ehz;
    private Float htz;
    private Float ltz;
    private Date htztm;
    private Date ltztm;

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

    public Float getAvz() {
        return avz;
    }

    public void setAvz(Float avz) {
        this.avz = avz;
    }

    public Float getEhz() {
        return ehz;
    }

    public void setEhz(Float ehz) {
        this.ehz = ehz;
    }

    public Float getHtz() {
        return htz;
    }

    public void setHtz(Float htz) {
        this.htz = htz;
    }

    public Float getLtz() {
        return ltz;
    }

    public void setLtz(Float ltz) {
        this.ltz = ltz;
    }

    public Date getHtztm() {
        return htztm;
    }

    public void setHtztm(Date htztm) {
        this.htztm = htztm;
    }

    public Date getLtztm() {
        return ltztm;
    }

    public void setLtztm(Date ltztm) {
        this.ltztm = ltztm;
    }
}
