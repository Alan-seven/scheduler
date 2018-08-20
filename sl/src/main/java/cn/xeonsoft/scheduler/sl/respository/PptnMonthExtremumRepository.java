package cn.xeonsoft.scheduler.sl.respository;

import java.util.Date;

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
public interface PptnMonthExtremumRepository {
	@Select("SELECT count(1) FROM STATIS_DYP WHERE stcd = #{stcd} AND yr >= #{yr} AND mnth = #{mnth}")
	int findCount(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth);

	@Insert("insert into STATIS_DYP(stcd,yr,mnth,mxdyp,mxtm,raindays)values(#{stcd},#{yr},#{mnth},#{mxdyp},#{mxtm},0)")
	void save(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth,@Param("mxdyp") Float mxdyp,@Param("mxtm") Date mxtm);
	
	@Update("update STATIS_DYP set raindays = #{days} where stcd = #{stcd} AND yr >= #{yr} AND mnth = #{mnth}")
	void updateRainDays(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth,@Param("days") Integer days);
	

	@Update("update STATIS_DYP set mxdyp = #{mxdyp} where stcd = #{stcd} AND yr >= #{yr} AND mnth = #{mnth}")
	void updateMxDyp(@Param("stcd") String stcd,@Param("yr") int yr,@Param("mnth") int mnth,@Param("mxdyp") Float mxdyp);
}