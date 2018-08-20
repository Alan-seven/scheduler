package cn.xeonsoft.worker.task.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.xeonsoft.worker.task.domain.StPstatR;

/**
 * 累计降水量ST_PSTAT_R
 * @author wantwantxu
 *
 */
public interface AccpRepository {
	@Insert("INSERT INTO ST_PSTAT_R(STCD,IDTM,STTDRCD,ACCP) values (#{stcd},#{idtm},#{sttdrcd},#{accp})")
	void save(StPstatR stPstatR);
	
	@Select("SELECT count(1) FROM ST_PSTAT_R WHERE stcd = #{stcd} AND idtm = #{idtm} AND sttdrcd = ${sttdrcd}")
	Integer findCount(StPstatR stPstatR);
	
	@Update("UPDATE ST_PSTAT_R SET ACCP = #{accp} WHERE stcd = #{stcd} AND idtm = #{idtm} AND sttdrcd = #{sttdrcd}")
	void update(StPstatR stPstatR);
}