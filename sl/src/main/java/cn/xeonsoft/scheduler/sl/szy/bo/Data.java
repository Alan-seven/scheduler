package cn.xeonsoft.scheduler.sl.szy.bo;

public class Data {

    private String id;
    private String tmId; // 指标时间
    private String itemId; // 测站编码
    private Float itemVl; // 指标值

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getTmId() {
        return tmId;
    }

    public void setTmId( String tmId ) {
        this.tmId = tmId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId( String itemId ) {
        this.itemId = itemId;
    }

    public Float getItemVl() {
        return itemVl;
    }

    public void setItemVl( Float itemVl ) {
        this.itemVl = itemVl;
    }
}
