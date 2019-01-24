package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 行政区划分的 雨量/蒸发量
 * @author wantwantxu
 *
 */
public interface StatisXierheRepository {

	@Select("SELECT electricityFlow,sluiceFlow,emissionFlow,electricityW,sluiceW,emissionW FROM STATIS_XIERHE WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}