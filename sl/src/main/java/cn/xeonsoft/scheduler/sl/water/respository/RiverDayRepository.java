package cn.xeonsoft.scheduler.sl.water.respository;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 日水位统计值
 * @author wantwantxu
 *
 */
public interface RiverDayRepository {

	@Select("SELECT COUNT(1) FROM STATIS_DZ WHERE stcd = #{stcd} AND tm = #{tm}")
	Integer findDZCount(@Param("stcd") String stcd,@Param("tm") String tm);

	@Insert("UPDATE STATIS_DZ SET AVZ = #{extremum} WHERE stcd = #{stcd} AND tm = #{tm}")
	void updateAvgz(Extreme extreme);

	@Insert("UPDATE STATIS_DZ SET EHZ = #{extremum} WHERE stcd = #{stcd} AND tm = #{tm}")
	void updateEhz(Extreme extreme);

	@Insert("UPDATE STATIS_DZ SET HTZ = #{extremum},HTZTM = #{extremumTm} WHERE stcd = #{stcd} AND tm = #{tm}")
	void updateHtzAndTm(Extreme extremem);

	@Insert("UPDATE STATIS_DZ SET LTZ = #{extremum},LTZTM = #{extremumTm} WHERE stcd = #{stcd} AND tm = #{tm}")
	void updateLtzAndTm(Extreme extremum);


	@Insert("INSERT INTO STATIS_DZ(STCD,TM,AVZ) VALUES(#{stcd},#{tm},#{extremum})")
	void saveAvgz(Extreme extremem);

	@Insert("INSERT INTO STATIS_DZ(STCD,TM,EHZ) VALUES(#{stcd},#{tm},#{extremum})")
	void saveEhz(Extreme extremem);

	@Insert("INSERT INTO STATIS_DZ(STCD,TM,HTZ,HTZTM) VALUES(#{stcd},#{tm},#{extremum},#{extremumTm})")
	void saveHtzAndTm(Extreme extremem);

	@Insert("INSERT INTO STATIS_DZ(STCD,TM,LTZ,LTZTM) VALUES(#{stcd},#{tm},#{extremum},#{extremumTm})")
	void saveLtzAndTm(Extreme extremem);

}