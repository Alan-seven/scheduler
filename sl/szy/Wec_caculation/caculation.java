package cn.xeonsoft.scheduler.sl.szy.Wec_caculation;

public class caculation {
	// 31条河流3种污染物纳污能力
	public static double[][] caculate() {
		for (int i = 0; i < parameter.river_number; i++) {
			for (int j = 0; j < 3; j++) {
				output.m[i][j] = caculation.一维模型(tacit.x[i], tacit.u[i], parameter.K,
						 input.qp[i], input.C0[i][j], tacit.Q[i],
						tacit.cs[j]);
				
			}
		}
		return output.m;
	}

	public static double 零维模型(double cp, double qp, double C0, double Q,
			double cs) {
		double c = (cp * qp + C0 * Q) / (Q + qp);
		double M0 = (cs - c) * (Q + qp);
		return M0;
	}
	

	//测量断面位于河道初始断面
	public static double 一维模型(double x, double u, double K, 
			double qp, double c0, double q, double cs) {
		double a = -1 * K * x / u;
		if (Double.isInfinite(u) || Double.isNaN(u)) {
			System.out.println("流速= " + u);
			System.out.println("请检查流速数据！！！");
		}
		double c_1 = (q * c0 ) / (q + qp);
		double c = c_1 * Math.exp(a);
		double M1 = parameter.xi *parameter.a* (cs - c) * (q + qp);
//		double c_1 = (q * c0 + qp * cp) / (q + qp);
//		double c = c_1 * Math.exp(a);
//		double M1 = parameter.xi *parameter.a* (cs - c) * (q + qp);		
		return M1;
	}
}
