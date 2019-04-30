package cn.xeonsoft.scheduler.sl.water.cache;

import cn.xeonsoft.scheduler.sl.rain.respository.AccpRepository;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xeonsoft.scheduler.utils.RedisUtil;

public class RiverDataCache {
	@Autowired
	private AccpRepository accpRepository;
	private static RedisUtil redisUtil = new RedisUtil();

	private static final String KEY_STPSTATR_ALL = "stpstatrAll";
}
