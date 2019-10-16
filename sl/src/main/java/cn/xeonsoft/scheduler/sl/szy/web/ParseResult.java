package cn.xeonsoft.scheduler.sl.szy.web;

import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import cn.xeonsoft.scheduler.utils.DateUtils;
import cn.xeonsoft.scheduler.utils.ItemEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParseResult {

    /**
     * 解析测站入口
     * @return
     */
    public List<WrStatB> getStationList(){
        String url = "http://10.10.1.20:8065/BaseService/MN_BaseExt/GetMN_BaseList?UserInfoID=95f244f5-d29d-4639-8f58-d02f9c5b063a";
        String result = getNewResult(url);
        if(StringUtils.isBlank(result)){
            return new ArrayList<WrStatB>();
        }
        List<WrStatB> statList = parseStation(result);
        return statList;
    }


    /**
     * 根据测站编码获取测站最新数据
     * @param stcd
     * @return
     */
    public List<Data> getNewData(String stcd){
        String url ="http://10.10.1.20:8065/BusinessService/MN_8051_MN/GetLatest8051?MN=";
        url= url+stcd;
        String result = getNewResult(url);
        if(StringUtils.isBlank(result)){
            return new ArrayList<Data>();
        }
        List<Data> dataList = parseNewData(result);
        return dataList;
    }

    /**
     * 发送请求
     * @return
     */
    public String getNewResult(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(1000*10);
            connection.setReadTimeout(1000*10);
            connection.setRequestProperty("Authorization", " CKYJKSY=87138313BDA2F91EB7DC2EEFACC2C456");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            connection.connect();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            BufferedReader bf=new BufferedReader(new InputStreamReader(in,"utf-8"));
            String line=null;
            String json = "";
            while((line=bf.readLine())!=null){//一行一行的读
                json = json + line;
            }
            if(in!=null){
                in.close();
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解析测站数据
     * @param str
     * @return
     */
    public List<WrStatB> parseStation(String str){
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(str).toString();
        JSONObject object = JSONObject.fromObject(resutStr);
        JSONArray array = object.getJSONArray("T_MN_BaseExtInfo");
        List<WrStatB> list = new ArrayList<WrStatB>();
        for(int i = 0 ; i < array.size();i++){
            JSONObject info = array.getJSONObject(i);
            String stcd = info.getString("MN");
            String stnm = info.getString("MNName");
            WrStatB entity = new WrStatB();
            entity.setStcd(stcd);
            entity.setStnm(stnm);
            list.add(entity);
        }
        return list;
    }

    /**
     * 解析水质最新周期数据
     * @param str
     * @return
     */
    public List<Data> parseNewData(String str){
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(str).toString();
        JSONObject object = JSONObject.fromObject(resutStr);
        JSONArray array = object.getJSONArray("MN_8051_MNExtInfo");
        List<Data> list = new ArrayList<Data>();
        for(int i = 0 ; i < array.size();i++){
            JSONObject info = array.getJSONObject(i);
            String tm = info.getString("DataTime");
            String itemVl = info.getString("DataValue");
            String itemName = info.getString("ParamName");
            Data entity = new Data();
            entity.setTm(DateUtils.parseDate(tm.replace("T", " ")));
            entity.setItemVl(Float.parseFloat(itemVl));
            String itemId = ItemEnum.getValue(itemName);
            entity.setItemId(itemId);
            list.add(entity);
        }
        return list;
    }

    public static void main(String[] args){
        ParseResult pr = new ParseResult();
        List<Data> list = pr.getNewData("08720044");
        for(Data vo : list){
            System.out.println(vo.getTm()+"--"+vo.getItemId()+"---"+vo.getItemVl());
        }
    }

}
