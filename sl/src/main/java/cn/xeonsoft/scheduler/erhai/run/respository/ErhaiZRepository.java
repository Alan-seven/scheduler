package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 洱海水位
 * @author wantwantxu
 *
 */
public interface ErhaiZRepository {

	@Select("SELECT avgz FROM STATIS_ERHAI_DAY WHERE tm = #{tm}")
	Float findDayZ(@Param("tm") String tm);

	@Select("SELECT avgz FROM STATIS_ERHAI_MONTH WHERE tm = #{tm}")
	Float findMonthZ(@Param("tm") String tm);

}