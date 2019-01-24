package cn.xeonsoft.scheduler.erhai.run.service;


public interface ErhaiRunService {
	Integer findCount(String sttdrcd,String tm);

	void updateDaliRain(String sttdrcd,String tm,Float daliRain);

	void updateNaturalInW(String sttdrcd,String tm,Float naturalInW);

	void updateCleanInw(String sttdrcd,String tm,Float cleanInW);

	void updateFirstDayZ(String sttdrcd,String tm,Float firstDayZ);

	void updateLastDayZ(String sttdrcd,String tm,Float lastDayZ);

	void updateOutuseW(String sttdrcd,String tm,Float outeruseW);

	void updateXierheOutW(String sttdrcd,String tm,Float xierheOutW);

	void updateYerbW(String sttdrcd,String tm,Float yerbW);

	void saveDaliRain(String sttdrcd,String tm,Float daliRain);
}