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
public interface DirectionFlowSumRepository {

	@Select("SELECT COUNT(1) FROM  WHERE tm = #{tm} and direction = #{direction} and sttdrcd = #{sttdrcd}")
	Integer findCount(@Param("tm") Date tm, @Param("direction") String direction, @Param("sttdrcd") String sttdrcd);

	@Insert("UPDATE STATIS_DIRECTION_SUMQ SET SUMQ = #{sumq} WHERE tm = #{tm} and direction = #{direction} and sttdrcd = #{sttdrcd}")
	void updateSumq(@Param("tm") Date tm, @Param("sumq") Float sumq, @Param("direction") String direction, @Param("sttdrcd") String sttdrcd);

	@Insert("INSERT INTO STATIS_DIRECTION_SUMQ(TM,SUMQ,direction,STTDRCD) VALUES(#{tm},#{sumq},#{direction},#{sttdrcd})")
	void saveSumq(@Param("tm") Date tm, @Param("sumq") Float sumq, @Param("direction") String direction, @Param("sttdrcd") String sttdrcd);

}