package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;

public interface StatisOuterUseService {
    OuterUse get(String sttdrcd, String tm);
}