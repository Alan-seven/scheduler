package cn.xeonsoft.scheduler.sl.szy.bo;

import java.util.Date;

/**
 * 纳污能力计算结果
 */
public class DataCrep {

    private String stcd;
    private Date tm;
    private Double cod;
    private Double tp;
    private Double tn;

    public String getStcd() {
        return stcd;
    }

    public void setStcd( String stcd ) {
        this.stcd = stcd;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm( Date tm ) {
        this.tm = tm;
    }

    public Double getCod() {
        return cod;
    }

    public void setCod( Double cod ) {
        this.cod = cod;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp( Double tp ) {
        this.tp = tp;
    }

    public Double getTn() {
        return tn;
    }

    public void setTn( Double tn ) {
        this.tn = tn;
    }
}
