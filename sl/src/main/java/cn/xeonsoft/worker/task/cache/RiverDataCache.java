package cn.xeonsoft.worker.task.cache;

import org.springframework.beans.factory.annotation.Autowired;

import cn.xeonsoft.worker.task.respository.AccpRepository;
import cn.xeonsoft.worker.task.utils.RedisUtil;

public class RiverDataCache {
	@Autowired
	private AccpRepository accpRepository;
	private static RedisUtil redisUtil = new RedisUtil();

	private static final String KEY_STPSTATR_ALL = "stpstatrAll";
}
