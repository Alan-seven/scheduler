package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;

public interface DayWService {

    Integer findRecordCount( DayW dayW );

    void updateRecord( DayW dayW );

    void saveRecord( DayW dayW );

}