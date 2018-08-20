package cn.xeonsoft.worker.task.respository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.worker.task.domain.RiverSt;

/**
 * 水位流量年极值
 * @author wantwantxu
 *
 */
public interface ZQYearExtremumRepository {
	@Select("SELECT stcd,tm,z FROM ST_RVYEVSQ_S WHERE yr >= #{yr}")
	List<RiverSt> findList(Integer yr);
}