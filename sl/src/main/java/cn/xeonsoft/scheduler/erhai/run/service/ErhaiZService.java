package cn.xeonsoft.scheduler.erhai.run.service;



public interface ErhaiZService {
	Float findDayZ();

	Float findDayZ(String tm);

	Float findMonthZ(String tm);
}