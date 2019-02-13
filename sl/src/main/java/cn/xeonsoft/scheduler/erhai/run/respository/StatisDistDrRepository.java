package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 行政区划分的 雨量
 * @author wantwantxu
 *
 */
public interface StatisDistDrRepository {

	@Select("SELECT tm,dyp FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm >= #{startDate} AND tm <= #{endDate} AND addvcd = #{addvcd}")
	List<Dali> list(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("addvcd") String addvcd);

}