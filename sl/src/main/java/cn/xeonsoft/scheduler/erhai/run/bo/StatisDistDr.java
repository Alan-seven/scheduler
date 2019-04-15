package cn.xeonsoft.scheduler.erhai.run.bo;

import java.util.Date;

/**
 * 洱海降雨分区统计（包含洱海流域降雨）
 */
public class StatisDistDr {

    private String id;
    private Date tm;
    private Float acce;  //蒸发量
    private Float dyp;      //降雨
    private String sttdrcd;
    private String addvcd;  //分区

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm( Date tm ) {
        this.tm = tm;
    }

    public Float getAcce() {
        return acce;
    }

    public void setAcce( Float acce ) {
        this.acce = acce;
    }

    public Float getDyp() {
        return dyp;
    }

    public void setDyp( Float dyp ) {
        this.dyp = dyp;
    }

    public String getSttdrcd() {
        return sttdrcd;
    }

    public void setSttdrcd( String sttdrcd ) {
        this.sttdrcd = sttdrcd;
    }

    public String getAddvcd() {
        return addvcd;
    }

    public void setAddvcd( String addvcd ) {
        this.addvcd = addvcd;
    }
}
