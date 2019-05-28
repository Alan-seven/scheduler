package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;

import java.util.Date;
import java.util.List;

public interface DataService {

    List<Data> list(String tmId);

    void saveRiverResult(List<StationTm> stationList);

    void saveErhaiResult(StationTm station);

    Integer findCount(String itemId, String tmId);

    void update(String tmId,String itemId,Float itemVl);

    void save(String id,String tmId,String itemId,Float itemVl);

    void save(List<Data> dataList,String tmId);
}
