package cn.xeonsoft.scheduler.sl.respository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.scheduler.sl.domain.Station;

/**
 * 站点
 * @author wantwantxu
 *
 */
public interface StationRepository {
	@Select("SELECT stcd,stnm FROM ST_STBPRP_B")
	List<Station> findList();
}