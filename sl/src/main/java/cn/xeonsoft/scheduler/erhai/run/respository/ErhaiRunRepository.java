package cn.xeonsoft.scheduler.erhai.run.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 洱海运行报表
 * @author wantwantxu
 *
 */
public interface ErhaiRunRepository {

	@Select("SELECT COUNT(1) FROM STATIS_ERHAI_RUN WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	Integer findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

	@Update("UPDATE STATIS_ERHAI_RUN SET DALI_RAIN = #{daliRain} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateDaliRain(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("daliRain") Float daliRain);

	@Update("UPDATE STATIS_ERHAI_RUN SET NATURAL_INW = #{naturalInWW} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateNaturalInW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("naturalInWW") Float naturalInWW);

	@Update("UPDATE STATIS_ERHAI_RUN SET CLEAN_INW = #{cleanInW} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateCleanInw(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("cleanInW") Float cleanInW);

	@Update("UPDATE STATIS_ERHAI_RUN SET FIRSTDAY_Z = #{firstDayZ} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateFirstDayZ(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("firstDayZ") Float firstDayZ);

	@Update("UPDATE STATIS_ERHAI_RUN SET LASTDAY_Z = #{lastDayZ} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateLastDayZ(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("lastDayZ") Float lastDayZ);

	@Update("UPDATE STATIS_ERHAI_RUN SET OUTERUSE_W = #{outeruseW} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateOutuseW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("outeruseW") Float outeruseW);

	@Update("UPDATE STATIS_ERHAI_RUN SET XIERHE_OUTW = #{xierheOutW} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateXierheOutW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("xierheOutW") Float xierheOutW);

	@Update("UPDATE STATIS_ERHAI_RUN SET YERB_W = #{yerbW} WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateYerbW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("yerbW") Float yerbW);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(STCD,TM,STTDRCD,DALI_RAIN) VALUES(#{stcd},#{tm},#{sttdrcd},#{daliRain})")
	void saveDaliRain(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("daliRain") Float daliRain);
}