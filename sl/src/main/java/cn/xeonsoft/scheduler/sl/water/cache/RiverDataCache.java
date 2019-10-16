package cn.xeonsoft.scheduler.sl.water.cache;

import cn.xeonsoft.scheduler.sl.rain.respository.AccpRepository;
import cn.xeonsoft.scheduler.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class RiverDataCache {
	@Autowired
	private AccpRepository accpRepository;
	private static RedisUtil redisUtil = new RedisUtil();

	private static final String KEY_STPSTATR_ALL = "stpstatrAll";
}
