package cn.xeonsoft.scheduler.sl.rain.respository;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 雨日、最大降雨量及发生时间
 * 
 * @author wantwantxu
 *
 */
public interface StatisDypRepository {
	@Select("SELECT count(1) FROM STATIS_DYP WHERE stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	int findCount(@Param("stcd") String stcd,@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);

	@Insert("insert into STATIS_DYP(stcd,tm,sttdrcd,mxdyp,mxtm,raindays)values(#{stcd},#{tm},#{sttdrcd},#{mxdyp},#{mxtm},0)")
	void save(@Param("stcd") String stcd,@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd,@Param("mxdyp") Float mxdyp,@Param("mxtm") Date mxtm);
	
	@Update("update STATIS_DYP set raindays = #{days} where stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateRainDays(@Param("days") int days,@Param("stcd") String stcd,@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);
	
	@Update("update STATIS_DYP set mxdyp = #{mxdyp},mxtm = #{mxtm} where stcd = #{stcd} AND tm = #{tm} AND sttdrcd = #{sttdrcd}")
	void updateMxDyp(@Param("mxdyp") Float mxdyp,@Param("mxtm") Date mxtm,@Param("stcd") String stcd,@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);
}