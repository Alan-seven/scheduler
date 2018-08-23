package cn.xeonsoft.scheduler.reptile.weather.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.gson.Gson;

import cn.xeonsoft.scheduler.reptile.utils.DateUtils;
import cn.xeonsoft.scheduler.reptile.utils.IdGen;
import cn.xeonsoft.scheduler.reptile.weather.bo.CityDZ;
import cn.xeonsoft.scheduler.reptile.weather.bo.WeatherInfo;
import cn.xeonsoft.scheduler.reptile.weather.domain.Weather;
import cn.xeonsoft.scheduler.reptile.weather.service.WeatherService;

/**
 * @author wantwantxu
 */
public class Every4HJob extends QuartzJobBean {
	Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private WeatherService weatherService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String json = doGet("http://d1.weather.com.cn/dingzhi/101290101.html?_=1534820965041");
		logger.warn(json);
		String[] jsonArr = json.split(";");
		if (null != jsonArr && jsonArr.length > 0) {
			String cityDZStr = jsonArr[0];
			int offset = cityDZStr.indexOf("=");
			if(offset > 0) {
				String weatherInfo = cityDZStr.substring(offset + 1);
				Gson gson = new Gson();
				CityDZ cityDZ = gson.fromJson(weatherInfo, CityDZ.class);
				WeatherInfo wi = cityDZ.getWeatherinfo();
				Weather weather = new Weather();
				weather.setId(IdGen.uuid());
				weather.setCityId("532901");
				weather.setTemp(wi.getTemp());
				weather.setTempn(wi.getTempn());
				weather.setTm(DateUtils.parseDate(wi.getFctime()));
				weather.setWd(wi.getWd());
				weather.setWeather(wi.getWeather());
				weather.setWs(wi.getWs());
				weatherService.save(weather);
			}
		}
	}

	public static String doGet(String httpurl) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(httpurl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod("GET");
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/61.0");
			connection.setRequestProperty("Referer", "http://m.weather.com.cn/m/pn3/weather.htm?id=101290101T");
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			connection.disconnect();// 关闭远程连接
		}

		return result;
	}
}