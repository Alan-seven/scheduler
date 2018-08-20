package cn.xeonsoft.scheduler.sl.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 河道水情均值
 * 
 * @author wantwantxu
 *
 */
public interface RiverMonthAvgRepository {
	@Insert("INSERT INTO ST_RVDMMYSQ_S(stcd,yr,mnth,prdtp,avz)values(#{stcd},#{yr},#{mnth},#{prdtp},#{avz})")
	void save(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth,@Param("prdtp") int prdtp,@Param("avz") Float avz);
	
	@Select("SELECT count(1) FROM ST_RVDMMYSQ_S WHERE stcd = #{stcd} AND yr=#{yr} AND mnth=#{mnth} AND prdtp = #{prdtp}")
	int findCount(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth,@Param("prdtp") int prdtp);
}