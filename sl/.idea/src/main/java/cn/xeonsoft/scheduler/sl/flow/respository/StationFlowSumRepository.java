package cn.xeonsoft.scheduler.sl.flow.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * 流量统计值
 * @author wantwantxu
 *
 */
public interface StationFlowSumRepository {

	@Select("SELECT COUNT(1) FROM STATIS_STATION_SUMQ WHERE stcd = #{stcd} and tm = #{tm} and sttdrcd = #{sttdrcd}")
	Integer findCount(@Param("stcd") String stcd,@Param("tm") Date tm,@Param("sttdrcd") String sttdrcd);

	@Insert("UPDATE STATIS_STATION_SUMQ SET SUMQ = #{sumq} WHERE stcd = #{stcd} and tm = #{tm} and sttdrcd = #{sttdrcd}")
	void updateSumq(@Param("stcd") String stcd, @Param("tm") Date tm, @Param("sumq") Float sumq, @Param("sttdrcd") String sttdrcd);

	@Insert("INSERT INTO STATIS_STATION_SUMQ(TM,STCD,SUMQ,STTDRCD) VALUES(#{tm},#{stcd},#{sumq},#{sttdrcd})")
	void saveSumq(@Param("stcd") String stcd,@Param("tm") Date tm, @Param("sumq") Float sumq, @Param("sttdrcd") String sttdrcd);

}