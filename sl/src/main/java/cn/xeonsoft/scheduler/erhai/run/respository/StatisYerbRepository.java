package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 引洱入滨水量
 * @author wantwantxu
 *
 */
public interface StatisYerbRepository {

	@Select("SELECT q FROM STATIS_YERB WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Float findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

}