package cn.xeonsoft.scheduler.sl.weather.respository;

import cn.xeonsoft.scheduler.sl.rain.bo.MoJi;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface MojiRepository {

    @Select("SELECT count(1) FROM DATA_MOJI WHERE tm = #{tm} and tp = #{tp}")
    Integer findCount(@Param("tm") Date tm, @Param("tp") String tp);

    @Insert("Insert into DATA_MOJI(tm,tp,result)values (#{tm},#{tp},#{result})")
    void save(@Param("tm") Date tm, @Param("tp") String tp, @Param("result") String result);

    @Select("SELECT tm,tp,result FROM DATA_MOJI WHERE tm = #{tm} and tp = #{tp}")
    MoJi find(@Param("tm") Date tm, @Param("tp") String tp);

}
