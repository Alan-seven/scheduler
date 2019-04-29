package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * 从wr_day_w_r 统计各水厂取用水的日，月，年数据，存入statis_wiu_w表中
 */
public interface WiuWRespository {

    @Select("SELECT count(1) FROM STATIS_WIU_W WHERE stcd = #{mpCd} AND tm = #{tm} and sttdrcd = #{sttdrcd} ")
    Integer findRecordCount( @Param("tm") Date tm,@Param("sttdrcd") String sttdrcd );

    @Update("UPDATE STATIS_WIU_W SET DAY_W = #{dayW} where stcd= #{mpCd} and tm = #{tm} and sttdrcd = #{sttdrcd}")
    void updateRecord(@Param("tm") Date tm,@Param("stcd") String stcd,@Param("w") Float w, @Param("sttdrcd") String sttdrcd);

    @Insert("INSERT INTO STATIS_WIU_W(tm,STCD,DAY_W,sttdrcd) VALUES(#{tm},#{stcd},#{dayW},#{sttdrcd})")
    void saveRecord(@Param("tm") Date tm,@Param("stcd") String stcd,@Param("w") Float w, @Param("sttdrcd") String sttdrcd );

    /**
     * 得到任意时间级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT mp_cd as mpCd,SUM(day_w) as dayW FROM wr_day_w_r a WHERE a.dt >= #{startDate}" +
            " and a.dt <= #{endDate}  group by a.mp_cd")
    List<DayW> findSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到小时级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT mp_cd as mpCd,CONVERT(CHAR(13),a.dt,20)+':00:00' as tm,SUM(day_w) as dayW FROM wr_day_w_r a" +
            " WHERE a.dt >= #{startDate} and a.dt <= #{endDate}  group by a.mp_cd,CONVERT(CHAR(13),a.dt,20)")
    List<DayW> findHourSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到日级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT mp_cd as mpCd,CONVERT(CHAR(10),a.dt,23) as tm,SUM(day_w) as dayW FROM wr_day_w_r a " +
            "WHERE a.dt >= #{startDate} and a.dt <= #{endDate}  group by a.mp_cd,CONVERT(CHAR(10),a.dt,23)")
    List<DayW> findDaySum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到月级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT mp_cd as mpCd,CONVERT(CHAR(7),dt,23)+'-01 00:00:00' as tm,SUM(day_w) as dayW FROM wr_day_w_r a " +
            "WHERE a.dt >= #{startDate} and a.dt <= #{endDate}  group by a.mp_cd,CONVERT(CHAR(7),a.dt,23)")
    List<DayW> findMonthSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到年级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT mp_cd as mpCd,CONVERT(CHAR(4),a.dt,23)+'-01-01 00:00:00' as tm,SUM(day_w) as dayW FROM wr_day_w_r a " +
            "WHERE a.dt >= #{startDate} and a.dt <= #{endDate}  group by a.mp_cd,CONVERT(CHAR(4),a.dt,23)")
    List<DayW> findYearSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
