package cn.xeonsoft.worker.task.respository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import cn.xeonsoft.worker.task.domain.RiverZQExtremum;

/**
 * 河道水情极值
 * 
 * @author wantwantxu
 *
 */
public interface RiverZQExtremumRepository {
	@Insert("INSERT INTO st_rvevs_r(stcd,idtm,sttdrcd,htz,ltz,mxq,mnq,htztm,ltztm,mxqtm,mnqtm)values(#{stcd},#{idtm},#{sttdrcd},#{htz},#{ltz},#{mxq},#{mnq},#{htztm},#{ltztm},#{mxqtm},#{mnqtm})")
	void save(RiverZQExtremum riverZQExtremum);

	@Update("UPDATE set_rvevs_r SET ltz = #{ltz},ltztm = #{ltztm} WHERE stcd = #{stcd} AND idtm = #{idtm} AND sttdrcd = #{sttdrcd}")
	void updateMinzAndTm(RiverZQExtremum riverZQExtremum);

	@Update("UPDATE set_rvevs_r SET mxq = #{mxq},mxqtm = #{mxqtm} WHERE stcd = #{stcd} AND idtm = #{idtm} AND sttdrcd = #{sttdrcd}")
	void updateMaxqAndTm(RiverZQExtremum riverZQExtremum);

	@Update("UPDATE set_rvevs_r SET mnq = #{mnq},mnqtm = #{mnqtm} WHERE stcd = #{stcd} AND idtm = #{idtm} AND sttdrcd = #{sttdrcd}")
	void updateMinqAndTm(RiverZQExtremum riverZQExtremum);
}