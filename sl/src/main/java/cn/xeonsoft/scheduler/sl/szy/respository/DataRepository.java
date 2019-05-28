package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 *  水质因子数据
 */
public interface DataRepository {

    @Select("SELECT id,tm_id as tmId,item_id as itemId,item_vl as itemVl FROM data WHERE tm_id = #{tmId} ")
    List<Data> list(String tmId);

    @Select("SELECT COUNT(1) FROM data WHERE item_id = #{itemId} and tm_id = #{tmId} ")
    Integer findCount(@Param("itemId") String itemId ,@Param("tmId") String tmId);

    @Insert("UPDATE data SET item_vl = #{itemVl} WHERE tm_id = #{tmId} and item_id = #{itemId}")
    void update(@Param("tmId") String tmId, @Param("itemId") String itemId, @Param("itemVl") Float itemVl);

    @Insert("INSERT INTO data(id,tm_id,item_id,item_vl) VALUES(#{id},#{tmId},#{itemId},#{itemVl})")
    void save( @Param("id") String id,@Param("tmId") String tmId, @Param("itemId") String itemId, @Param("itemVl") Float itemVl);

}
