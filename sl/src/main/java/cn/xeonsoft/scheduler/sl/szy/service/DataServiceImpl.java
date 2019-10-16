package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.caculation;
import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.input;
import cn.xeonsoft.scheduler.sl.szy.Wec_caculation.parameter;
import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.DataCrep;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.respository.DataRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
     * @param
     * @return
     */
    public List<Data> list(String tmId){
        return this.dataRepository.list(tmId);
    }

    /**
     * 根据最新的水质数据，获取指定的水质因子
     * @param dataList
     * @param target
     * @return
     */
    public Data getVal(List<Data> dataList , String target){
        Data data = null;
        for(Data item : dataList){
            if(target.equals(item.getItemId())){
                data = item;
                break;
            }
        }
        return data;
    };

    //保存纳污能力计算结果
    public void  saveRiverResult(List<StationTm> stationList){
        double result[][]=new double [parameter.river_number][3];
        String[][] stcd = new String[stationList.size()][2];
        //如果查询出来的河流水质小于31条，不做计算
        if(stationList.size() == parameter.river_number){
            //写入模型计算输入条件
            for(int i = 0 ; i < stationList.size();i++){
                stcd[i][0] = stationList.get(i).getStcd();
                stcd[i][1] = DateUtils.formatDateTime(stationList.get(i).getTm());
                //得到每次水质监测的因子值
                List<Data> dataList = dataRepository.list(stationList.get(i).getId());
                String tmId = stationList.get(i).getId();
                Data cod = getVal(dataList,"COD");
                Data tp = getVal(dataList,"TP");
                Data tn = getVal(dataList,"TN");
                input.qp[i]=50;
                input.C0[i][0] = cod!=null?cod.getItemVl():0;
                input.C0[i][1] = tn!=null?tn.getItemVl():0;
                input.C0[i][2] = tp!=null?tp.getItemVl():0;
            }
            //调用计算程序
            result = caculation.caculate();
            //保存计算结果
            for(int j = 0 ; j < result.length; j++){
                DataCrep crep = new DataCrep();
                crep.setStcd(stcd[j][0]);
                Date tm = DateUtils.parseDate(stcd[j][1]);
                System.out.println("tm"+stcd[j][1]);
                crep.setTm(tm);
                crep.setCod(getMath(1,result[j][0]));
                crep.setTp(getMath(1,result[j][1]));
                crep.setTn(getMath(1,result[j][2]));
                if(dataCrepService.findRecordCount(crep)<=0){
                    dataCrepService.saveRecord(crep);
                }else{
                    dataCrepService.updateRecord(crep);
                }
            }
        }
    }

    public static double getMath(int digit,double input){
        BigDecimal bg = new BigDecimal(input);
        double data = bg.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
        return data;
    }

    //保存洱海纳污能力计算结果
    public void  saveErhaiResult(StationTm station){
        String tmId = station.getId();
        //得到每次水质监测的因子值
        List<Data> dataList = dataRepository.list(tmId);
        Data cod = getVal(dataList,"COD");
        Data tp = getVal(dataList,"TP");
        Data tn = getVal(dataList,"TN");
        Data nh3 = getVal(dataList,"NH3");

        cn.xeonsoft.scheduler.sl.szy.erhai_caculation.input.H_er = 8.15d ;
        cn.xeonsoft.scheduler.sl.szy.erhai_caculation.input.C0_er[0] = cod!=null?cod.getItemVl():0;
        cn.xeonsoft.scheduler.sl.szy.erhai_caculation.input.C0_er[1] = tn!=null?tn.getItemVl():0;
        cn.xeonsoft.scheduler.sl.szy.erhai_caculation.input.C0_er[2] = tp!=null?tp.getItemVl():0;
        cn.xeonsoft.scheduler.sl.szy.erhai_caculation.input.C0_er[3] = nh3!=null?nh3.getItemVl():0;

        //保存洱海计算结果
        DataCrep crep = new DataCrep();
        crep.setStcd(station.getStcd());
        Date tm = DateUtils.parseDate(station.getTm());
        crep.setTm(tm);
        crep.setCod(cn.xeonsoft.scheduler.sl.szy.erhai_caculation.output.er_m[0]);
        crep.setTp(cn.xeonsoft.scheduler.sl.szy.erhai_caculation.output.er_m[1]);
        crep.setTn(cn.xeonsoft.scheduler.sl.szy.erhai_caculation.output.er_m[2]);
        crep.setNh3(cn.xeonsoft.scheduler.sl.szy.erhai_caculation.output.er_m[3]);
        if(dataCrepService.findRecordCount(crep)<=0){
            dataCrepService.saveRecord(crep);
        }else{
            dataCrepService.updateRecord(crep);
        }
    }

    @Override
    public Integer findCount(String itmId, String tmId) {
        return dataRepository.findCount(itmId,tmId);
    }

    @Override
    public void update(String tmId, String itemId, Float itemVl) {
        dataRepository.update(tmId, itemId, itemVl);
    }

    @Override
    public void save(String id, String tmId, String itemId, Float itemVl) {
        dataRepository.save(id,tmId,itemId,itemVl);
    }

    @Override
    public void save(List<Data> dataList,String tmId){
        for(int i = 0 ; i < dataList.size(); i++){
            String id = UUID.randomUUID().toString().replace("-","");
            String itemId = dataList.get(i).getItemId();
            Float itemVl = dataList.get(i).getItemVl();
            if(findCount(itemId,tmId)<=0){
                save(id,tmId,itemId,itemVl);
            }

        }
    }

}
