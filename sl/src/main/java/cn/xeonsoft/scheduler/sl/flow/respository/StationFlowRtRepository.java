package cn.xeonsoft.scheduler.sl.flow.respository;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
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
}