package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 行政区划分的 雨量/蒸发量
 * @author wantwantxu
 *
 */
public interface StatisYerbRepository {

	@Select("SELECT COUNT(1) FROM STATIS_YERB WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}