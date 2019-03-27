package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * 河道泵站日用水量统计
 * @seven
 */
public interface DayWRepository {

    @Select("SELECT count(1) FROM wr_st_dayw_r WHERE stcd = #{stcd} AND dt = #{dt} ")
    Integer findRecordCount( DayW dayW );

    @Update("UPDATE wr_st_dayw_r SET DAY_W = #{dayW} where stcd= #{stcd} and dt = #{dt} ")
    void updateRecord( DayW dayW );

    @Insert("INSERT INTO wr_st_dayw_r(STCD,DT,DAY_W) VALUES(#{stcd},#{dt},#{dayW})")
    void saveRecord( DayW dayW );

}
