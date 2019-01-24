package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 行政区划分的 雨量/蒸发量
 * @author wantwantxu
 *
 */
public interface StatisDistDrRepository {

	@Select("SELECT COUNT(1) FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm = #{tm} AND addvcd = #{addvcd}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("addvcd") String addvcd);

}