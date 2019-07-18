package cn.xeonsoft.scheduler.sl.szy.bo;

import java.util.Date;

public class StationTm {

    private String id;
    private String stcd;
    private Date tm;
    private String wqg;

    private String startDate;
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd( String stcd ) {
        this.stcd = stcd;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate( String startDate ) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate( String endDate ) {
        this.endDate = endDate;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getWqg() {
        return wqg;
    }

    public void setWqg(String wqg) {
        this.wqg = wqg;
    }
}
