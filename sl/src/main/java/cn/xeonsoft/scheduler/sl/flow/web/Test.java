package cn.xeonsoft.scheduler.sl.flow.web;

import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        Date beginDate = DateUtils.getBeginDate(DateInterval.YEAR);
        cal.setTime(beginDate);
        for(int i = 0 ; i < 224;i++){
            System.out.println(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
    }

}
