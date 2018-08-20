package cn.xeonsoft.worker.task.respository;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.worker.task.bo.ZQExtremum;

/**
 * 雨量
 * @author wantwantxu
 *
 */
public interface RiverDayRepository {

	@Select("SELECT COUNT(1) FROM DATA_RIVER_DAY WHERE stcd = #{stcd} AND tm = #{tm}")
	Integer findCount(@Param("stcd") String stcd,@Param("tm") Date tm);

	@Insert("INSERT INTO DATA_RIVER_DAY(STCD,TM,Z) VALUES(#{stcd},#{tm},#{extremum})")
	void saveAvz(@Param("stcd") String stcd,@Param("tm") Date tm,@Param("extremum") Float extremum);

	@Insert("UPDATE DATA_RIVER_DAY SET Z = #{extremum} WHERE stcd = #{stcd} AND tm = #{tm}")
	void updateAvz(@Param("stcd") String stcd,@Param("tm") Date tm,@Param("extremum") Float extremum);
}