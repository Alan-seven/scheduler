package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 泵站统计为农业用水数据，存入 statis_pump_sum
 */
public interface PumpSumRepository {

    @Select("SELECT COUNT(1) FROM STATIS_PUMP_SUM WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    Integer findCount(@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);

    @Insert("UPDATE STATIS_PUMP_SUM SET w = #{w} WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    void updateSumw(@Param("tm") Date tm,@Param("w") Float w,@Param("sttdrcd") String sttdrcd);

    @Insert("INSERT INTO STATIS_PUMP_SUM(tm,W,STTDRCD) VALUES(#{tm},#{w},#{sttdrcd})")
    void saveSumw( @Param("tm") Date tm, @Param("w") Float w, @Param("sttdrcd") String sttdrcd);

    /**
     * 得到小时级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(a.pmpq) as dayW FROM st_pump_r a WHERE  a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(13),a.tm,20)")
    List<DayW> findHourSum(  @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到日级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(10),a.tm,23) as tm,SUM(a.pmpq) as dayW FROM st_pump_r a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(10),a.tm,23)")
    List<DayW> findDaySum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到月级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(7),tm,23) as tm,SUM(a.pmpq) as dayW FROM st_pump_r a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(7),tm,23)")
    List<DayW> findMonthSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到年级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(4),a.tm,23) as tm,SUM(a.pmpq) as dayW FROM st_pump_r a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(4),a.tm,23)")
    List<DayW> findYearSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    /**
     * 得到任意时间段的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT SUM(a.pmpq) as dayW FROM st_pump_r a WHERE a.tm >= #{startDate} and a.tm <= #{endDate}")
    List<DayW> findSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
