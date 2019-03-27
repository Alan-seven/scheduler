package cn.xeonsoft.scheduler.sl.szy.Wec_caculation;

public class tacit {
	// 默认参数
	// 河流纳污能力计算参数（系数）
	public static int river_number = 31;
	// 河段长度m
	public static double x[] = { 200, 141.29, 188.91, 164.63, 185.49, 182.65,
			650.35, 412.94, 200, 200, 394.22, 546.5, 127.37, 388.95, 140.68,
			96.99, 1076.18, 65.17, 450.02, 111.35, 285.02, 1000, 1000, 200,
			200, 200, 200, 200, 200, 200, 200 };
	// 设计流量下河道断面的平均流速，m/s
	public static double u[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
	// 各河平水年流量，单位m3/s
	public static double Q[] = { 200, 200, 200, 200, 200, 200, 200, 200, 200,
			200, 200, 200, 200, 200, 200,  200,  200,  200,  200, 200, 200, 200, 200,
			200, 200, 200, 200, 200, 200, 200, 200 };
	// 目标污染物浓度
	 public static double cs[]= {15,0.5,0.1};//cod,tn,tp
//	public static double cs[] = { 8, 8, 8 };// cod,tn,tp,测试用
	public static double cod_tp_lake = 0.25;
}
