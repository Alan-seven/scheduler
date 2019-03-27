package cn.xeonsoft.scheduler.sl.szy.respository;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  水质因子数据
 */
public interface DataRepository {

    @Select("SELECT id,tm_id as tmId,item_id as itemId,item_vl as itemVl FROM data WHERE tm_id = #{tmId} ")
    List<Data> list(String tmId);

    @Select("SELECT id,tm_id as tmId,item_id as itemId,item_vl as itemVl FROM data WHERE tm_id = #{tmId} and item_id='COD' ")
    Data getCod(String tmId);

    @Select("SELECT id,tm_id as tmId,item_id as itemId,item_vl as itemVl FROM data WHERE tm_id = #{tmId} and item_id='TP' ")
    Data getTp(String tmId);

    @Select("SELECT id,tm_id as tmId,item_id as itemId,item_vl as itemVl FROM data WHERE tm_id = #{tmId}  and item_id='TN'")
    Data getTn(String tmId);
}
