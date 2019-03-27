package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.caculation;
import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.input;
import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.parameter;
import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.DataCrep;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import cn.xeonsoft.scheduler.sl.szy.respository.DataRepository;
import cn.xeonsoft.scheduler.sl.szy.respository.DayWRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component("dataService")
@Transactional
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private DataCrepService dataCrepService;
    @Autowired
    private WrStatBService wrStatBService;

    /**
     * 查询时间段类的数据
     * @param data
     * @return
     */
    public List<Data> list(String tmId){
        return this.dataRepository.list(tmId);
    }

    public Data getCod(String tmId){
        return this.dataRepository.getCod(tmId);
    };

    public Data getTp(String tmId){
        return this.dataRepository.getTp(tmId);
    };

    public Data getTn(String tmId){
        return this.dataRepository.getTn(tmId);
    };

    //保存纳污能力计算结果
    public void  saveResult(List<StationTm> stationList){
        double m[][]=new double [parameter.river_number][3];
        String[][] stcd = new String[stationList.size()][2];
        //如果查询出来的河流水质小于31条，不做计算
        if(stationList.size() == parameter.river_number){
            //写入模型计算输入条件
            for(int i = 0 ; i < stationList.size();i++){
                stcd[i][0] = stationList.get(i).getStcd();
                stcd[i][1] = stationList.get(i).getTm();
                //得到每次水质监测的因子值
                List<Data> dataList = dataRepository.list(stationList.get(i).getId());
                String tmId = stationList.get(i).getId();
                Data cod = getCod(tmId);
                Data tp = getTp(tmId);
                Data tn = getTn(tmId);
                input.qp[i]=50;
                input.C0[i][0] = cod!=null?cod.getItemVl():0;
                input.C0[i][1] = tn!=null?tn.getItemVl():0;
                input.C0[i][2] = tp!=null?tp.getItemVl():0;
            }
            //调用计算程序
            m= caculation.caculate();
            //保存计算结果
            for(int j = 0 ; j < m.length; j++){
                DataCrep crep = new DataCrep();
                crep.setStcd(stcd[j][0]);
                Date tm = DateUtils.parseDate(stcd[j][1]);
                crep.setTm(tm);
                crep.setCod(m[j][0]);
                crep.setTp(m[j][1]);
                crep.setTn(m[j][2]);
                if(dataCrepService.findRecordCount(crep)<=0){
                    dataCrepService.saveRecord(crep);
                }
            }
        }
    }

}
