package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WrStatBRepository {

    @Select("SELECT stcd,st_nm as stnm FROM station_tm WHERE st_tp = #{sttp} ")
    List<WrStatB> list( String sttp);
}
