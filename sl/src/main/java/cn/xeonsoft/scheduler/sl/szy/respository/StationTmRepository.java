package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  水质数据存储
 */
public interface StationTmRepository {

    @Select("SELECT id,stcd,tm FROM station_tm WHERE tm >= #{startDate} AND tm < #{endDate} ")
    List<StationTm> list(String beginDate,String endDate);
}
