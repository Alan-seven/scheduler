package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * 河道泵站日用水量统计
 * @seven
 */
public interface DayWRepository {

    @Select("SELECT count(1) FROM STATIS_PUMP_W WHERE stcd = #{stcd} AND tm = #{tm} and sttdrcd = #{sttdrcd} ")
    Integer findRecordCount( DayW dayW );

    @Update("UPDATE STATIS_PUMP_W SET DAY_W = #{dayW} where stcd= #{stcd} and tm = #{tm} and sttdrcd = #{sttdrcd}")
    void updateRecord( DayW dayW );

    @Insert("INSERT INTO STATIS_PUMP_W(tm,STCD,DAY_W,sttdrcd) VALUES(#{tm},#{stcd},#{dayW},#{sttdrcd})")
    void saveRecord( DayW dayW );

    /**
     * 得到任意时间级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT stcd,SUM(DAY_W) as dayW FROM STATIS_PUMP_W a WHERE a.tm >= #{startDate}" +
            " and a.tm <= #{endDate} and sttdrcd='8' group by a.stcd")
    List<DayW> findSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到小时级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT stcd,CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(DAY_W) as dayW FROM STATIS_PUMP_W a" +
            " WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and sttdrcd='8' group by a.stcd,CONVERT(CHAR(13),a.tm,20)")
    List<DayW> findHourSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到日级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT stcd,CONVERT(CHAR(10),a.TM,23) as tm,SUM(DAY_W) as dayW FROM STATIS_PUMP_W a " +
            "WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and sttdrcd='8' group by a.stcd,CONVERT(CHAR(10),a.TM,23)")
    List<DayW> findDaySum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到月级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT stcd,CONVERT(CHAR(7),tm,23)+'-01 00:00:00' as tm,SUM(DAY_W) as dayW FROM STATIS_PUMP_W a " +
            "WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and sttdrcd='8' group by a.stcd,CONVERT(CHAR(7),a.tm,23)")
    List<DayW> findMonthSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到年级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT stcd,CONVERT(CHAR(4),a.TM,23)+'-01-01 00:00:00' as tm,SUM(DAY_W) as dayW FROM STATIS_PUMP_W a " +
            "WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and sttdrcd='8' group by a.stcd,CONVERT(CHAR(4),a.tm,23)")
    List<DayW> findYearSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
