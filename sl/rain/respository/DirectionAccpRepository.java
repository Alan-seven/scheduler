package cn.xeonsoft.scheduler.sl.rain.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * STATIS_DIRECTION_ACCP
 * @author wantwantxu
 *
 */
public interface DirectionAccpRepository {
	@Insert("INSERT INTO STATIS_DIRECTION_ACCP(tm,direction,sttdrcd,accp) values (#{tm},#{direction},#{sttdrcd},#{accp})")
	void save(Date tm, String direction, String sttdrcd, Float accp);
	
	@Select("SELECT count(1) FROM STATIS_DIRECTION_ACCP WHERE tm = #{tm} AND direction = #{direction} AND sttdrcd = #{sttdrcd}")
	Integer findCount(Date tm, String direction, String sttdrcd);

	@Update("UPDATE STATIS_DIRECTION_ACCP SET accp = #{accp} WHERE tm = #{tm} AND direction = #{direction} AND sttdrcd = #{sttdrcd}")
	void update(Float accp,Date tm, String direction, String sttdrcd);
}