package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 从wr_day_w_r 统计所有水厂取用水的日，月，年数据，存入statis_wiu_sumw表中
 */
public interface WiuSumRepository {

    @Select("SELECT COUNT(1) FROM STATIS_WIU_SUMW WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    Integer findCount(@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);

    @Insert("UPDATE STATIS_WIU_SUMW SET w = #{w} WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    void updateSumw(@Param("tm") Date tm,@Param("w") Float w,@Param("sttdrcd") String sttdrcd);

    @Insert("INSERT INTO STATIS_WIU_SUMW(tm,W,STTDRCD) VALUES(#{tm},#{w},#{sttdrcd})")
    void saveSumw( @Param("tm") Date tm, @Param("w") Float w, @Param("sttdrcd") String sttdrcd);

    @Select("SELECT COUNT(1) FROM STATIS_OUTERUSE WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    Integer findLifeCount(@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);

    @Insert("UPDATE STATIS_OUTERUSE SET life = #{w} WHERE tm = #{tm} and sttdrcd = #{sttdrcd}")
    void updateLifew(@Param("tm") Date tm,@Param("w") Float w,@Param("sttdrcd") String sttdrcd);

    @Insert("INSERT INTO STATIS_OUTERUSE(id,tm,life,STTDRCD) VALUES(#{id},#{tm},#{w},#{sttdrcd})")
    void saveLifew(@Param("id") String id, @Param("tm") Date tm, @Param("w") Float w, @Param("sttdrcd") String sttdrcd);


    /**
     * 得到小时级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(13),a.dt,20)+':00:00' as tm,SUM(a.day_w) as dayW FROM wr_day_w_r a WHERE  a.dt >= #{startDate} and a.dt <= #{endDate} group by CONVERT(CHAR(13),a.dt,20)")
    List<DayW> findHourSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到日级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(10),a.dt,23) as tm,SUM(a.day_w) as dayW FROM wr_day_w_r a WHERE a.dt >= #{startDate} and a.dt <= #{endDate} group by CONVERT(CHAR(10),a.dt,23)")
    List<DayW> findDaySum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到月级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(7),dt,23) as tm,SUM(a.day_w) as dayW FROM wr_day_w_r a WHERE a.dt >= #{startDate} and a.dt <= #{endDate} group by CONVERT(CHAR(7),dt,23)")
    List<DayW> findMonthSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 得到年级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT CONVERT(CHAR(4),a.dt,23) as tm,SUM(a.day_w) as dayW FROM wr_day_w_r a WHERE a.dt >= #{startDate} and a.dt <= #{endDate} group by CONVERT(CHAR(4),a.dt,23)")
    List<DayW> findYearSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    /**
     * 得到任意时间段的合计
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("SELECT SUM(a.day_w) as dayW FROM wr_day_w_r a WHERE a.dt >= #{startDate} and a.dt <= #{endDate}")
    List<DayW> findSum( @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
