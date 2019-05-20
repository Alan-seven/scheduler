package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;

import java.util.List;

public interface DataService {

    List<Data> list(String tmId);

    void saveRiverResult(List<StationTm> stationList);

    void saveErhaiResult(StationTm station);
}
