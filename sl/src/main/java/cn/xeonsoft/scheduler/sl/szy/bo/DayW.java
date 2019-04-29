package cn.xeonsoft.scheduler.sl.szy.bo;

import java.util.Date;

/**
 * 泵站水量
 */
public class DayW {

    private String stcd;
    private String tm;
    private Float dayW;
    private String sttdrcd;

    private String mpCd;

    public String getStcd() {
        return stcd;
    }

    public void setStcd( String stcd ) {
        this.stcd = stcd;
    }

    public Float getDayW() {
        return dayW;
    }

    public void setDayW( Float dayW ) {
        this.dayW = dayW;
    }

    public String getSttdrcd() {
        return sttdrcd;
    }

    public void setSttdrcd( String sttdrcd ) {
        this.sttdrcd = sttdrcd;
    }

    public String getMpCd() {
        return mpCd;
    }

    public void setMpCd( String mpCd ) {
        this.mpCd = mpCd;
    }

    public String getTm() {
        return tm;
    }

    public void setTm( String tm ) {
        this.tm = tm;
    }
}
