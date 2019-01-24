package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 天然入湖/净入湖
 * @author wantwantxu
 *
 */
public interface InWRepository {

	@Select("SELECT COUNT(1) FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm = #{tm} AND addvcd = #{addvcd}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("addvcd") String addvcd);

}