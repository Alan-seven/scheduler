package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.Erhai;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 洱海水位
 * @author wantwantxu
 *
 */
public interface ErhaiRepository {
	@Select("SELECT top 1 avgz FROM STATIS_ERHAI WHERE tm >= #{startDate} AND tm <= #{endDate} AND sttdrcd = #{sttdrcd} ORDER BY tm asc")
	Float findFirstZ(@Param("sttdrcd") String sttdrcd,@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("SELECT top 1 avgz FROM STATIS_ERHAI WHERE tm >= #{startDate} AND tm <= #{endDate} AND sttdrcd = #{sttdrcd} ORDER BY tm desc")
	Float findLastZ(@Param("sttdrcd") String sttdrcd,@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("SELECT tm,avgz FROM STATIS_ERHAI WHERE tm >= #{startDate} AND tm <= #{endDate} AND sttdrcd = #{sttdrcd}")
	List<Erhai> findBySttdrcd(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("sttdrcd") String sttdrcd);

}