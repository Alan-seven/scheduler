package cn.xeonsoft.scheduler.sl.respository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.domain.RiverDay;
import cn.xeonsoft.scheduler.sl.domain.RiverSt;

/**
 * 实时河道水情
 * 
 * @author wantwantxu
 *
 */
public interface RiverRtRepository {

	@Select("SELECT top 1 STCD,Z FROM ST_RIVER_R WHERE tm >= #{startDate} and tm <= #{endDate}  and stcd = #{stcd} ORDER BY tm desc")
	List<RiverDay> find8hList(@Param("stcd") String stcd, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Select("SELECT stcd,tm,z FROM ST_RIVER_R WHERE tm >= #{startDate} and tm <= #{endDate}")
	List<RiverSt> findList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到平均水位
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("select STCD,AVG(Z) as extremum from ST_RIVER_R where TM >= #{startDate} and TM <= #{endDate} group by stcd")
	List<ZQExtremum> findAvgz(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到最大水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("select top 1 stcd,tm,MAX(z) as extremum from ST_RIVER_R where "
			+ "STCD = #{stcd} and TM >= #{startDate} and TM <= #{endDate}" + " group by stcd,tm order by extremum desc")
	ZQExtremum findMaxzAndTm(@Param("stcd") String stcd, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/**
	 * 得到最小水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("select top 1 stcd,tm,MIN(z) as extremum from ST_RIVER_R where "
			+ "STCD = #{stcd} and TM >= #{startDate} and TM <= #{endDate}" + " group by stcd,tm order by extremum asc")
	ZQExtremum findMinzAndTm(@Param("stcd") String stcd, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}