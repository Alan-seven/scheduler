package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Xierhe;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 西洱河出水量
 * @author wantwantxu
 *
 */
public interface StatisXierheRepository {

	@Select("SELECT electricityW,sluiceW,emissionW FROM STATIS_XIERHE WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Xierhe get(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}