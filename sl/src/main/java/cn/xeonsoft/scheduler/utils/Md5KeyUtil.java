package cn.xeonsoft.scheduler.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * MD5加密，获取key
 */
public class Md5KeyUtil {
    static final String USER_AGENT = "Mozilla/5.0";
    static final String MOJI_TOKEN="a8c01907b793b49fc683fe757936a4e6";
    static final String MOJI_PASSWD = "49fc0b0b44a26e64493fc453e6c6d4fc";
    static String[] chars = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public static String getKey(String nonce,String suburl,Long ts){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String str = MOJI_PASSWD+"\n"+ts+"\n"+nonce+"\n"+suburl;

        byte[] result = md5.digest(str.getBytes());

        StringBuilder sb = new StringBuilder(32);
        // 将结果转为16进制字符  0~9 A~F
        for (int i = 0; i < result.length; i++) {
            // 一个字节对应两个字符
            byte x = result[i];
            // 取得高位
            int h = 0x0f & (x >>> 4);
            // 取得低位
            int l = 0x0f & x;
            sb.append(chars[h]).append(chars[l]);
        }
        return sb.toString();
    }

    public String getResult(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(1000*100);
            connection.setReadTimeout(1000*100);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
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

}
