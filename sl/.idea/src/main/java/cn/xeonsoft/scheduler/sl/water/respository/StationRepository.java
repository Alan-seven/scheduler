package cn.xeonsoft.scheduler.sl.water.respository;

import java.util.List;

import cn.xeonsoft.scheduler.sl.water.domain.Station;
import org.apache.ibatis.annotations.Select;

/**
 * 站点
 * @author wantwantxu
 *
 */
public interface StationRepository {
	@Select("SELECT stcd,stnm FROM ST_STBPRP_B")
	List<Station> findList();
}