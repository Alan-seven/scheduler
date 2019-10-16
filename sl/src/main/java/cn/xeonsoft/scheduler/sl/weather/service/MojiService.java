package cn.xeonsoft.scheduler.sl.weather.service;

import cn.xeonsoft.scheduler.sl.rain.bo.MoJi;

import java.util.Date;

/**
 * 墨迹天气数据入库
 */
public interface MojiService {

    Integer findCount(Date tm, String tp);

    void save(Date tm, String tp, String result);

    MoJi find(Date tm, String tp);
}
