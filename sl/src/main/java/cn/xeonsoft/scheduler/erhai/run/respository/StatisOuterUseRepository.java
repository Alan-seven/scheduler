package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 河道外用水
 * @author wantwantxu
 *
 */
public interface StatisOuterUseRepository {

	@Select("SELECT LIFE,INDUSTRY,FARMING FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}