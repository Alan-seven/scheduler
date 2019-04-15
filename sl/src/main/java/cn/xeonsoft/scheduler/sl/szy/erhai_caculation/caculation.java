package cn.xeonsoft.scheduler.sl.szy.erhai_caculation;

public class caculation {

	public static double[] er_caculate() {
	
			for (int j = 0; j < 4; j++) {
				output.er_m[j] = caculation.非均匀混合(tacit.er_cs[j],
						input.C0_er[j], tacit.qp_er, parameter.k_er[j],
						input.H_er, parameter.r_dis, parameter.ksj);	
		}
		return output.er_m;
	}

	public static double 非均匀混合(double cs, double c0, double qp, double kp,
			double h, double r, double ksj) {
		double m = 0;
		m = parameter.xi * (cs - c0) * Math.exp(kp * ksj * h * r * r / 2 / qp)
				* qp;
		return m;
	}

	public static double model_vollenweider(double cs,double c0, double A, double Z,
			double nuo, double Qa, double V) {
		double m = 0;
		m = parameter.xi*((cs-c0) * A * Z * (nuo/24/365/3600 + Qa / V));
		return m;
	}
}
