package cn.xeonsoft.scheduler.sl.flow.respository;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 实时河道水情
 * 
 * @author wantwantxu
 *
 */
public interface StationFlowRtRepository {
	@Select("SELECT COUNT(1) FROM STATIS_STATION_SUMQ WHERE stcd = #{stcd} and tm = #{tm} and sttdrcd = #{sttdrcd}")
	Integer findCount(@Param("stcd") String stcd,@Param("tm") String tm,@Param("sttdrcd") String sttdrcd);
	/**
	 * 得到任意时间级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,SUM(a.Q) as sumq FROM ST_RIVER_R a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by a.stcd")
	List<FlowSum> findSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到小时级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by a.stcd,CONVERT(CHAR(13),a.tm,20)")
	List<FlowSum> findHourSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到日级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(10),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by a.stcd,CONVERT(CHAR(10),a.TM,23)")
	List<FlowSum> findDaySum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到月级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(7),tm,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by a.stcd,CONVERT(CHAR(7),a.tm,23)")
	List<FlowSum> findMonthSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到年级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(4),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} group by a.stcd,CONVERT(CHAR(4),a.tm,23)")
	List<FlowSum> findYearSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


	/**
	 * 根据已知的西洱河时刻用水量，得到任意时间级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,SUM(isnull(a.sumq,0)) as sumq FROM STATIS_STATION_SUMQ a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and a.stcd='90210530' and a.sttdrcd='0' group by a.stcd")
	List<FlowSum> findXierheSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 根据已知的西洱河时刻用水量，得到小时级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(isnull(a.sumq,0)) as sumq FROM STATIS_STATION_SUMQ a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and a.stcd='90210530' and a.sttdrcd='0'  group by a.stcd,CONVERT(CHAR(13),a.tm,20)")
	List<FlowSum> findXierheHourSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 根据已知的西洱河时刻用水量，得到日级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(10),a.TM,23) as tm,SUM(isnull(a.sumq,0)) as sumq FROM STATIS_STATION_SUMQ a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and a.stcd='90210530' and a.sttdrcd='0' group by a.stcd,CONVERT(CHAR(10),a.TM,23)")
	List<FlowSum> findXierheDaySum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 根据已知的西洱河时刻用水量，得到月级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(7),tm,23) as tm,SUM(isnull(a.sumq,0)) as sumq FROM STATIS_STATION_SUMQ a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and a.stcd='90210530' and a.sttdrcd='0'  group by a.stcd,CONVERT(CHAR(7),a.tm,23)")
	List<FlowSum> findXierheMonthSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 根据已知的西洱河时刻用水量，得到年级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT stcd,CONVERT(CHAR(4),a.TM,23) as tm,SUM(isnull(a.sumq,0)) as sumq FROM STATIS_STATION_SUMQ a WHERE a.tm >= #{startDate} and a.tm <= #{endDate} and a.stcd='90210530' and a.sttdrcd='0'  group by a.stcd,CONVERT(CHAR(4),a.tm,23)")
	List<FlowSum> findXierheYearSum(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Insert("UPDATE ST_RIVER_R SET q = #{q} WHERE stcd = #{stcd} and tm = #{tm} ")
	void updateQ(@Param("stcd") String stcd, @Param("tm") Date tm, @Param("q") Float q);

	@Insert("UPDATE ST_RIVER_R SET z = #{z} WHERE stcd = #{stcd} and tm = #{tm} ")
	void updateZ(@Param("stcd") String stcd, @Param("tm") Date tm, @Param("z") Float z);
}