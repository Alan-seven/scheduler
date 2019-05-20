package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.szy.bo.DataCrep;
import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 水质纳污能力统计
 * @seven
 */
public interface DataCrepRepository {

    @Select("SELECT count(1) FROM data_crep WHERE stcd = #{stcd} AND tm = #{tm} ")
    Integer findRecordCount( DataCrep crep );

    @Update("UPDATE data_crep SET cod = #{cod},tp=#{tp},tn=#{tn},nh3 = #{nh3} where stcd= #{stcd} and tm = #{tm}  ")
    void updateRecord(DataCrep crep  );

    @Insert("INSERT INTO data_crep(STCD,tm,cod,tp,tn,nh3) VALUES(#{stcd},#{tm},#{cod},#{tp},#{tn},#{nh3} )")
    void saveRecord( DataCrep crep  );

}
