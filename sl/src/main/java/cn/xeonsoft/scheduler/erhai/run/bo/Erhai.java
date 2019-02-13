package cn.xeonsoft.scheduler.erhai.run.bo;

public class Erhai {
    private String tm;
    private Float avgz;
    private Float firstZ;   //月初水位
    private Float lastZ;   //月未水位

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public Float getAvgz() {
        return avgz;
    }

    public void setAvgz(Float avgz) {
        this.avgz = avgz;
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
}
