package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import cn.xeonsoft.scheduler.sl.szy.respository.WrStatBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("wrStatBService")
@Transactional
public class WrStatBServiceImpl implements  WrStatBService{

    @Autowired
    private WrStatBRepository wrStatBRepository;

    @Override
    public List<WrStatB> list( String sttp ) {
        return this.wrStatBRepository.list(sttp);
    }
}
