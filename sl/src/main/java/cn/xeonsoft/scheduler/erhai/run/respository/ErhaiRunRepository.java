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

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,DALI_RAIN) VALUES(#{id},#{tm},#{sttdrcd},#{daliRain})")
	void saveDaliRain(@Param("id") String id, @Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("daliRain") Float daliRain);
	@Update("UPDATE STATIS_ERHAI_RUN SET DALI_RAIN = #{daliRain} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateDaliRain(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("daliRain") Float daliRain);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,NATURAL_INW) VALUES(#{id},#{tm},#{sttdrcd},#{naturalInW})")
	void saveNaturalInW(@Param("id") String id, @Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("naturalInW") Float naturalInW);
	@Update("UPDATE STATIS_ERHAI_RUN SET NATURAL_INW = #{naturalInW} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateNaturalInW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("naturalInW") Float naturalInW);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,CLEAN_INW) VALUES(#{id},#{tm},#{sttdrcd},#{cleanInW})")
	void saveCleanInw(@Param("id") String id,@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("cleanInW") Float cleanInW);
	@Update("UPDATE STATIS_ERHAI_RUN SET CLEAN_INW = #{cleanInW} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateCleanInw(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("cleanInW") Float cleanInW);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,FIRSTDAY_Z) VALUES(#{id},#{tm},#{sttdrcd},#{firstDayZ})")
	void saveFirstDayZ(@Param("id") String id,@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("firstDayZ") Float firstDayZ);
	@Update("UPDATE STATIS_ERHAI_RUN SET FIRSTDAY_Z = #{firstDayZ} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateFirstDayZ(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("firstDayZ") Float firstDayZ);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,LASTDAY_Z) VALUES(#{id},#{tm},#{sttdrcd},#{lastDayZ})")
	void saveLastDayZ(@Param("id") String id,@Param("sttdrcd") String sttdrcd,@Param("tm") String tm,@Param("lastDayZ") Float lastDayZ);
	@Update("UPDATE STATIS_ERHAI_RUN SET LASTDAY_Z = #{lastDayZ} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateLastDayZ(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("lastDayZ") Float lastDayZ);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,OUTERUSE_W) VALUES(#{id},#{tm},#{sttdrcd},#{outeruseW})")
	void saveOutuseW(@Param("id") String id,@Param("sttdrcd") String sttdrcd,@Param("tm") String tm, @Param("outeruseW") Float outeruseW);
	@Update("UPDATE STATIS_ERHAI_RUN SET OUTERUSE_W = #{outeruseW} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateOutuseW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("outeruseW") Float outeruseW);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,XIERHE_OUTW) VALUES(#{id},#{tm},#{sttdrcd},#{xierheOutW})")
	void saveXierheOutW(@Param("id") String id,@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("xierheOutW") Float xierheOutW);
	@Update("UPDATE STATIS_ERHAI_RUN SET XIERHE_OUTW = #{xierheOutW} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateXierheOutW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("xierheOutW") Float xierheOutW);

	@Insert("INSERT INTO STATIS_ERHAI_RUN(ID,TM,STTDRCD,YERB_W) VALUES(#{id},#{tm},#{sttdrcd},#{yerbW})")
	void saveYerbW(@Param("id") String id,@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("yerbW") Float yerbW);
	@Update("UPDATE STATIS_ERHAI_RUN SET YERB_W = #{yerbW} WHERE tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateYerbW(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm, @Param("yerbW") Float yerbW);
}