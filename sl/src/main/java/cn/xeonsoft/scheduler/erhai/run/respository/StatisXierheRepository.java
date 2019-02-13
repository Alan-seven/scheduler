package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Xierhe;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 西洱河出水量
 * @author wantwantxu
 *
 */
public interface StatisXierheRepository {

	@Select("SELECT tm,electricityW,sluiceW,emissionW FROM STATIS_XIERHE WHERE sttdrcd = #{sttdrcd} AND tm >= #{startDate} AND tm <= #{endDate}")
	List<Xierhe> list(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}