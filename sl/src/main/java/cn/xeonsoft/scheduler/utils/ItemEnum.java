package cn.xeonsoft.scheduler.utils;

public enum ItemEnum {

    A("水温","C"), B("pH","PH"),C("溶解氧","DOX"),D("电导率","COND"),E("浊度","TU"),F("CODMn","CODMn"),G("氨氮","NH4N"),H("总磷","TP"),I("总氮","TN"),
    J("砷","As"),K("六价铬","Cr"),L("阴离子表面活性剂","Ais"),M("硫化物","SN"),N("氟化物","FL"),O("CODcr","COD"),
    P("蓝绿藻","BGA"),Q("叶绿素","CHL"),R("环境温度","WT"),S("环境湿度","RH"),T("铜","Cu"),W("镉","CD"),X("铅","PB");
    private String name;
    private String value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    private ItemEnum(String name,String value){
        this.name= name;
        this.value = value;
    }

    public static String getValue(String code) {
        for (ItemEnum ele : values()) {
            if(ele.getName().equals(code)) return ele.getValue();
        }
        return null;
    }
}
