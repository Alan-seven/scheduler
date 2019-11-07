package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface StationTmRepository {


    /**
     * 查询31条河流的水质数据
     * @param beginDate
     * @param endDate
     * @return
     */
    @Select("SELECT a.id,a.stcd,a.tm FROM station_tm a,(select a.stcd,max(a.tm) as tm from station_tm a WHERE a.tm >= #{beginDate} AND a.tm < #{endDate} " +
            " group by a.stcd ) rr where a.stcd = rr.stcd  and a.tm = rr.tm ")
    List<StationTm> listByRiver( String beginDate, String endDate);

    /**
     * 查询洱海的水质数据
     * @param beginDate
     * @param endDate
     * @return
     */
    @Select("SELECT id,stcd,tm FROM station_tm WHERE tm >= #{beginDate} AND tm < #{endDate} and  stcd='90210532' ")
    StationTm getByErhai(String beginDate,String endDate);

    @Select("SELECT COUNT(1) FROM station_tm WHERE stcd = #{stcd} and tm = #{tm} ")
    Integer findCount(@Param("stcd") String stcd,@Param("tm") Date tm);

    @Insert("UPDATE station_tm SET wqg = #{wqg} WHERE stcd = #{stcd} and tm = #{tm} ")
    void update(@Param("wqg") String wqg, @Param("stcd") String stcd,@Param("tm") Date tm);

    @Insert("INSERT INTO station_tm(id,stcd,tm,WQG) VALUES(#{id},#{stcd},#{tm},#{wqg})")
    void save( @Param("id") String id,@Param("stcd") String stcd, @Param("tm") Date tm, @Param("wqg") String wqg);

    @Select("SELECT id,stcd,tm FROM station_tm WHERE stcd = #{stcd} and tm = #{tm} ")
    StationTm get(@Param("stcd") String stcd,@Param("tm") Date tm);
}
