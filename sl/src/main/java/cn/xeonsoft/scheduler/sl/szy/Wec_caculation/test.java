package cn.xeonsoft.scheduler.sl.szy.Wec_caculation;
public class test {
	public static void main(String args[]) throws Exception {
		System.out.println("run...");		
		double m[][]=new double [parameter.river_number][3];
		//第一步，给input所有变量赋值，交后台处理
		for (int i = 0; i < m.length; i++) {			
			input.qp[i]=50;				
			input.C0[i][0]=6;
			input.C0[i][1]=0.5;
			input.C0[i][2]=0.2;
		}
		//第二步，调用计算程序
		m=caculation.caculate();
		//输出结果 [31][3]数组
		System.out.println("纳污能力为"+m[21][2]);
		System.out.println("cacution_over");
	}	
}
