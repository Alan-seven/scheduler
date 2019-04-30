package cn.xeonsoft.scheduler.sl.rain.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 旬月累计降水量
 * 
 * @author wantwantxu
 *
 */
public interface PptnMonthDrpRepository {
	@Select("SELECT count(1) FROM ST_PDMMYSQ_S WHERE yr >= #{yr} and mnth = #{mnth} and prdtp = #{prdtp} and stcd = #{stcd}")
	Integer findCount(@Param("yr") Integer yr, @Param("mnth") Integer mnth, @Param("prdtp") Integer prdtp, @Param("stcd") String stcd);

	@Insert("Insert into ST_PDMMYSQ_S(stcd,yr,mnth,prdtp,accp)values (#{stcd},#{yr},#{mnth},#{prdtp},#{accp})")
	void save(@Param("stcd") String stcd, @Param("yr") Integer yr, @Param("mnth") Integer mnth,
			@Param("prdtp") Integer prdtp, @Param("accp") Float accp);

	@Update("UPDATE ST_PDMMYSQ_S SET accp = #{accp} WHERE stcd = #{stcd} and yr = #{yr} and mnth = #{mnth} and prdtp = #{prdtp}")
	void update(@Param("stcd") String stcd, @Param("yr") Integer yr, @Param("mnth") Integer mnth,
			@Param("prdtp") Integer prdtp, @Param("accp") Float accp);
}