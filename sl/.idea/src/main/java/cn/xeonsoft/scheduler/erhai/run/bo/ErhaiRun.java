package cn.xeonsoft.scheduler.erhai.run.bo;

import java.util.Date;

public class ErhaiRun {
    private Date tm;
    private Float rain;
    private Float naturalW;
    private Float flow;
    private Float firstZ;
    private Float lastZ;
    private Float outerW;
    private Float xierheW;
    private Float yerbW;

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public Float getRain() {
        return rain;
    }

    public void setRain(Float rain) {
        this.rain = rain;
    }

    public Float getNaturalW() {
        return naturalW;
    }

    public void setNaturalW(Float naturalW) {
        this.naturalW = naturalW;
    }

    public Float getFlow() {
        return flow;
    }

    public void setFlow(Float flow) {
        this.flow = flow;
    }

    public Float getFirstZ() {
        return firstZ;
    }

    public void setFirstZ(Float firstZ) {
        this.firstZ = firstZ;
    }

    public Float getLastZ() {
        return lastZ;
    }

    public void setLastZ(Float lastZ) {
        this.lastZ = lastZ;
    }

    public Float getOuterW() {
        return outerW;
    }

    public void setOuterW(Float outerW) {
        this.outerW = outerW;
    }

    public Float getXierheW() {
        return xierheW;
    }

    public void setXierheW(Float xierheW) {
        this.xierheW = xierheW;
    }

    public Float getYerbW() {
        return yerbW;
    }

    public void setYerbW(Float yerbW) {
        this.yerbW = yerbW;
    }
}
