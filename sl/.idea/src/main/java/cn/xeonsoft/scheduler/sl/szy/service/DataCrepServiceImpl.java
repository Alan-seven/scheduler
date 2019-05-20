package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DataCrep;
import cn.xeonsoft.scheduler.sl.szy.respository.DataCrepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("dataCrepService")
@Transactional
public class DataCrepServiceImpl implements  DataCrepService {

    @Autowired
    private DataCrepRepository dataCrepRepository;

    @Override
    public Integer findRecordCount( DataCrep crep ) {
        return this.dataCrepRepository.findRecordCount(crep);
    }

    @Override
    public void updateRecord( DataCrep crep ) {
        this.dataCrepRepository.updateRecord(crep);
    }

    @Override
    public void saveRecord( DataCrep crep ) {
        this.dataCrepRepository.saveRecord(crep);
    }
}
