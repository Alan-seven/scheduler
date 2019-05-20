package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import cn.xeonsoft.scheduler.erhai.run.bo.StatisDistDr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * 行政区划分的 雨量
 * @author wantwantxu
 *
 */
public interface StatisDistDrRepository {

	@Select("SELECT tm,dyp FROM STATIS_DIST_DR WHERE sttdrcd = #{sttdrcd} AND tm >= #{startDate} AND tm <= #{endDate} AND addvcd = #{addvcd}")
	List<Dali> list(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("addvcd") String addvcd);

	@Insert("INSERT INTO STATIS_DIST_DR(id,acce,dyp,sttdrcd,tm,addvcd) values (#{id},#{acce},#{dyp},#{sttdrcd},#{tm},#{addvcd})")
	void save(StatisDistDr statisDistDr);

	@Select("SELECT count(1) FROM STATIS_DIST_DR WHERE tm = #{tm} AND addvcd = #{addvcd} AND sttdrcd = ${sttdrcd}")
	Integer findCount(StatisDistDr statisDistDr);

	@Update("UPDATE STATIS_DIST_DR SET dyp = #{dyp} WHERE tm = #{tm} AND addvcd = #{addvcd} AND sttdrcd = ${sttdrcd}")
	void update(StatisDistDr statisDistDr);
}