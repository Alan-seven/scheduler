package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;

import java.util.List;

public interface DataService {

    List<Data> list(String tmId);

    Data getCod(String tmId);

    Data getTp(String tmId);

    Data getTn(String tmId);

    void saveResult(List<StationTm> stationList);
}
