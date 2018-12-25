package cn.xeonsoft.scheduler.sl.flow.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 流量统计值
 * @author wantwantxu
 *
 */
public interface FlowSumRepository {

	@Select("SELECT COUNT(1) FROM STATIS_SUMQ WHERE tm = #{tm} and type = #{type} and sttdrcd = #{sttdrcd}")
	Integer findCount(@Param("tm") String tm,@Param("type") String type,@Param("sttdrcd") String sttdrcd);

	@Insert("UPDATE STATIS_SUMQ SET SUMQ = #{sumq} WHERE tm = #{tm} and type = #{type} and sttdrcd = #{sttdrcd}")
	void updateSumq(@Param("tm") String tm,@Param("sumq") Float sumq,@Param("type") String type,@Param("sttdrcd") String sttdrcd);

	@Insert("INSERT INTO STATIS_SUMQ(TM,SUMQ,TYPE,STTDRCD) VALUES(#{tm},#{sumq},#{type},#{sttdrcd})")
	void saveSumq(@Param("tm") String tm,@Param("sumq") Float sumq,@Param("type") String type,@Param("sttdrcd") String sttdrcd);

}