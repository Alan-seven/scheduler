package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 河道外用水
 * @author wantwantxu
 *
 */
public interface StatisOuterUseRepository {

	@Select("SELECT TM,LIFE,INDUSTRY,FARMING FROM STATIS_OUTERUSE WHERE sttdrcd = #{sttdrcd} AND tm >= #{startDate} AND tm <= #{endDate}")
	List<OuterUse> list(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}