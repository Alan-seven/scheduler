package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.InW;

import java.util.List;

public interface InWService {
	List<InW> listYearOrMonth(Integer yr, Integer mnth);
}