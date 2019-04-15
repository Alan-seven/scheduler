package cn.xeonsoft.scheduler.sl.szy.erhai_caculation;

public class test {
	public static void main(String args[]) throws Exception {
		System.out.println("run...");		
		// 计算洱海纳污能力，COD,TN,TP,NH3;
		double m_er[] = new double[4];// 依次代表COD,TN,TP,NH3;
		// 第一步，给input中洱海的所有变量赋值，交后台处理
		input.H_er = 8;
		input.C0_er[0] = 8;
		input.C0_er[1] = 0.2;
		input.C0_er[2] = 0.02;
		input.C0_er[3] = 0.15;
		// 第二步，调用洱海纳污能力计算程序
		m_er = caculation.er_caculate();
		// 第三步，输出结果 [4]数组,output.er_m
		for (int i = 0; i < 4; i++) {
			System.out.println("第" + i + "种污染物纳污能力=" + output.er_m[i]);
		}

		System.out.println("cacution_over");
	}
}
