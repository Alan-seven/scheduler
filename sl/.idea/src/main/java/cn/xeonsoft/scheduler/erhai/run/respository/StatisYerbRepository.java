package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import cn.xeonsoft.scheduler.erhai.run.bo.Yerb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 引洱入滨水量
 * @author wantwantxu
 *
 */
public interface StatisYerbRepository {

	@Select("SELECT count(1) FROM STATIS_YERB WHERE sttdrcd = #{sttdrcd} AND tm = #{tm}")
	int findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

	@Select("SELECT tm,w FROM STATIS_YERB WHERE sttdrcd = #{sttdrcd} AND tm >= #{startDate} AND tm <= #{endDate}")
	List<Yerb> list(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}