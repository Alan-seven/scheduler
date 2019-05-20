package cn.xeonsoft.scheduler.sl.flow.respository;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.domain.RiverSt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 实时河道水情
 *
 * 按 入湖出湖分组/东南西北分区进行分钟、小时、日、月、年、任意时段的合计流量统计
 * @author wantwantxu
 *
 */
public interface FlowRtRepository {
	/**
	 * 得到分钟级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(16),a.tm,20) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(16),a.tm,20)")
	List<FlowSum> findMinuteSum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到小时级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(13),a.tm,20)")
	List<FlowSum> findHourSum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到日级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(10),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(10),a.TM,23)")
	List<FlowSum> findDaySum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到月级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(7),tm,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(7),tm,23)")
	List<FlowSum> findMonthSum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到年级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(4),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(4),a.TM,23)")
	List<FlowSum> findYearSum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	/**
	 * 得到任意时间段的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.type = #{type} and a.tm >= #{startDate} and a.tm <= #{endDate}")
	List<FlowSum> findSum(@Param("type") String type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


	//------------------------------------------------------------------------东南西北分区统计-------------------------------------------------
	/**
	 * 得到分钟级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(16),a.tm,20) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(16),a.tm,20)")
	List<FlowSum> findMinuteSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到小时级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(13),a.tm,20)")
	List<FlowSum> findHourSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到日级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(10),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(10),a.TM,23)")
	List<FlowSum> findDaySumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到月级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(7),tm,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(7),tm,23)")
	List<FlowSum> findMonthSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到年级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(4),a.TM,23) as tm,SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(4),a.TM,23)")
	List<FlowSum> findYearSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	/**
	 * 得到任意时间段的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT SUM(a.Q) as sumq FROM ST_RIVER_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate}")
	List<FlowSum> findSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}