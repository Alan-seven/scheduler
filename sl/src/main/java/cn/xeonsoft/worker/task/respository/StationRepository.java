package cn.xeonsoft.worker.task.respository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.worker.task.domain.Station;

/**
 * 站点
 * @author wantwantxu
 *
 */
public interface StationRepository {
	@Select("SELECT stcd,stnm FROM ST_STBPRP_B")
	List<Station> findList();
}