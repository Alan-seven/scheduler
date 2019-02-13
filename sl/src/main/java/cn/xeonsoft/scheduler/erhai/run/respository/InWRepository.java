package cn.xeonsoft.scheduler.erhai.run.respository;

import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 天然入湖/净入湖
 * @author wantwantxu
 *
 */
public interface InWRepository {

	@Select("SELECT yr,mnth,flow,w FROM T_SFRD_FORECAST WHERE yr = #{yr} AND mnth = #{mnth}")
	List<InW> listYearOrMonth(@Param("yr") Integer yr, @Param("mnth") Integer mnth);

}