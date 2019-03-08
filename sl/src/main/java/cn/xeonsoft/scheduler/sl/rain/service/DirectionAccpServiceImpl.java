package cn.xeonsoft.scheduler.sl.rain.service;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.respository.DirectionAccpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author wantwantxu
 */
@Component("directionAccpService")
@Transactional
public class DirectionAccpServiceImpl implements DirectionAccpService {
	@Autowired
	private DirectionAccpRepository directionAccpRepository;

	@Override
	public void save(List<Accp> list, String direction, String sttdrcd) {
		for(Accp accp : list){
			save(accp.getTm(),direction,sttdrcd,accp.getAccp());
		}
	}

	@Override
	public void save(Date tm, String direction, String sttdrcd, Float accp) {
		directionAccpRepository.save(tm,direction,sttdrcd,accp);
	}

	@Override
	public Integer findCount(Date tm, String direction, String sttdrcd) {
		return directionAccpRepository.findCount(tm,direction,sttdrcd);
	}

	@Override
	public void update(Date tm, String direction, String sttdrcd) {
		directionAccpRepository.update(tm,direction,sttdrcd);
	}
}
