package cn.xeonsoft.scheduler.utils;

public enum DateInterval {
	// 1一日,2三日,3一侯,4一旬,5一月,6一年,7一周,8一季
	MINUTE(0),DAY(1),THREEDAYS(2),FIVEDAYS(3),TENDAYS(4),WEEK(7), MONTH(5), QUARTER(8), YEAR(6),HOUR(9);

	private int type;

	private DateInterval(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
