package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StationTmRepository {


    /**
     * 查询31条河流的水质数据
     * @param beginDate
     * @param endDate
     * @return
     */
    @Select("SELECT id,stcd,tm FROM station_tm WHERE tm >= #{startDate} AND tm < #{endDate} and  EXISTS (select b.stcd from wr_stat_b b where b.st_tp='WQ' and a.stcd=b.stcd )")
    List<StationTm> listByRiver( String beginDate, String endDate);

    /**
     * 查询洱海的水质数据
     * @param beginDate
     * @param endDate
     * @return
     */
    @Select("SELECT id,stcd,tm FROM station_tm WHERE tm >= #{startDate} AND tm < #{endDate} and  stcd='90210532' ")
    StationTm getByErhai(String beginDate,String endDate);

}
