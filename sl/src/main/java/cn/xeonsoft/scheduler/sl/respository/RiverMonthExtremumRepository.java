package cn.xeonsoft.scheduler.sl.respository;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 河道水情极值
 * 
 * @author wantwantxu
 *
 */
public interface RiverMonthExtremumRepository {
	@Select("SELECT COUNT(1) FROM ST_RVDMEVSQ_S WHERE stcd = #{stcd} and yr = #{yr} and mnth = #{mnth} and prdtp = #{prdtp}")
	int findCount(@Param("stcd") String stcd, @Param("yr") int yr, @Param("mnth") int mnth, @Param("prdtp") int prdtp);

	@Insert("INSERT INTO ST_RVDMEVSQ_S(stcd,yr,mnth,prdtp,htz,htztm)values(#{stcd},#{yr},#{mnth},#{prdtp},#{htz},#{htztm})")
	void saveHtzAndTm(@Param("stcd") String stcd, @Param("yr") int yr, @Param("mnth") int mnth,
			@Param("prdtp") int prdtp, @Param("htz") Float htz, @Param("htztm") Date htztm);

	@Insert("INSERT INTO ST_RVDMEVSQ_S(stcd,yr,mnth,prdtp,ltz,ltztm)values(#{stcd},#{yr},#{mnth},#{prdtp},#{ltz},#{ltztm})")
	void saveLtzAndTm(@Param("stcd") String stcd, @Param("yr") int yr, @Param("mnth") int mnth,
			@Param("prdtp") int prdtp, @Param("ltz") Float ltz, @Param("htztm") Date htztm);

	@Update("UPDATE ST_RVDMEVSQ_S SET htz = #{htz},htztm = #{htztm} WHERE stcd = #{stcd} and yr = #{yr} and mnth = #{mnth} and prdtp = #{prdtp}")
	void updateHtzAndTm(@Param("stcd") String stcd, @Param("yr") int yr, @Param("mnth") int mnth,
			@Param("prdtp") int prdtp, @Param("htz") Float htz, @Param("htztm") Date htztm);

	@Update("UPDATE ST_RVDMEVSQ_S SET ltz = #{ltz},ltztm = #{ltztm} WHERE stcd = #{stcd} and yr = #{yr} and mnth = #{mnth} and prdtp = #{prdtp}")
	void updateLtzAndTm(@Param("stcd") String stcd, @Param("yr") int yr, @Param("mnth") int mnth,
			@Param("prdtp") int prdtp, @Param("ltz") Float ltz, @Param("ltztm") Date ltztm);
	
	@Delete("DELETE FROM ST_RVDMEVSQ_S ")
	void removeAll();
}