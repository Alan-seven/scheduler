package cn.xeonsoft.scheduler.sl.water.respository;

import java.util.List;

import cn.xeonsoft.scheduler.sl.water.domain.RiverSt;
import org.apache.ibatis.annotations.Select;

/**
 * 水位流量年极值
 * @author wantwantxu
 *
 */
public interface ZQYearExtremumRepository {
	@Select("SELECT stcd,tm,z FROM ST_RVYEVSQ_S WHERE yr >= #{yr}")
	List<RiverSt> findList(Integer yr);
}