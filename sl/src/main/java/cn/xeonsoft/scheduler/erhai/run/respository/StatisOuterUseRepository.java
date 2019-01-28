package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 河道外用水
 * @author wantwantxu
 *
 */
public interface StatisOuterUseRepository {

	@Select("SELECT LIFE,INDUSTRY,FARMING FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	OuterUse get(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}