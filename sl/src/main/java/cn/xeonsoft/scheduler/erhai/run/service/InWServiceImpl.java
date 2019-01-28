package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiZRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.InWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("inWService")
@Transactional
public class InWServiceImpl implements InWService {
	@Autowired
	private InWRepository inWRepository;

    @Override
    public InW get(Integer yr, Integer mnth) {
        return inWRepository.get(yr,mnth);
    }
}
