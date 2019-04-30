package cn.xeonsoft.scheduler.sl.szy.Wec_caculation;

public class input {
	
	//参数输入数组[31], i=0~30分别代表31条河，见说明。 
	//污染物种类0~cod,1~tn,2~tp	
	public static double C0[][] = new double[parameter.river_number][3];// 断面初始浓度或背景浓度值, mg/L
	public static double qp[] = new double[parameter.river_number];// 各河废污水排放流量，单位m3/s	
//	public static double Cp[][] = new double[parameter.river_number][3];// 排污口排放浓度,mg/L
}
