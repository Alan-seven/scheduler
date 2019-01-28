package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 行政区划分的 雨量
 * @author wantwantxu
 *
 */
public interface StatisDistDrRepository {

	@Select("SELECT dyp FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm = #{tm} AND addvcd = #{addvcd}")
	Float get(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("addvcd") String addvcd);

}