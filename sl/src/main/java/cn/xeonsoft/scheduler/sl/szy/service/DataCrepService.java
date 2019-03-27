package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DataCrep;
import cn.xeonsoft.scheduler.sl.szy.bo.DayW;

public interface DataCrepService {
    Integer findRecordCount(DataCrep crep );

    void updateRecord(DataCrep crep );

    void saveRecord(DataCrep crep );
}
