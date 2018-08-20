package cn.xeonsoft.scheduler.sl.cache;

import org.springframework.beans.factory.annotation.Autowired;

import cn.xeonsoft.scheduler.sl.respository.AccpRepository;
import cn.xeonsoft.scheduler.sl.utils.RedisUtil;

public class RiverDataCache {
	@Autowired
	private AccpRepository accpRepository;
	private static RedisUtil redisUtil = new RedisUtil();

	private static final String KEY_STPSTATR_ALL = "stpstatrAll";
}
