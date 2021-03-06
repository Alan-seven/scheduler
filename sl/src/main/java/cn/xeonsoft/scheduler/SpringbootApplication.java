package cn.xeonsoft.scheduler;

import cn.xeonsoft.scheduler.erhai.run.job.ErhaiRun1Job;
import cn.xeonsoft.scheduler.sl.flow.job.Flow1HJob;
import cn.xeonsoft.scheduler.sl.flow.job.StationFlow1HJob;
import cn.xeonsoft.scheduler.sl.rain.job.Rain1HJob;
import cn.xeonsoft.scheduler.sl.szy.job.*;
import cn.xeonsoft.scheduler.sl.weather.job.Weather1HJob;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cn.xeonsoft.scheduler.sl.water.job.Water1HJob;

@MapperScan(basePackages = "cn.xeonsoft.scheduler.*.*.respository")
@SpringBootApplication()
public class SpringbootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public JobDetail water1hJobDetail() {
		return JobBuilder.newJob(Water1HJob.class).withIdentity("water1h").storeDurably().build();
	}

	@Bean
	public JobDetail rain1hJobDetail() {
		return JobBuilder.newJob(Rain1HJob.class).withIdentity("rain1h").storeDurably().build();
	}

	@Bean
	public JobDetail flow1hJobDetail() {
		return JobBuilder.newJob(Flow1HJob.class).withIdentity("flow1h").storeDurably().build();
	}

	@Bean
	public JobDetail stationFlow1hJobDetail() {
		return JobBuilder.newJob(StationFlow1HJob.class).withIdentity("stationFlow1h").storeDurably().build();
	}

	@Bean
	public JobDetail erhaiRun1hJobDetail() {
		return JobBuilder.newJob(ErhaiRun1Job.class).withIdentity("erhaiRun1h").storeDurably().build();
	}

	@Bean
	public JobDetail crep4hJobDetail() {
		return JobBuilder.newJob(Data4HJob.class).withIdentity("crep4h").storeDurably().build();
	}

	@Bean
	public JobDetail stationPumpW1hJobDetail() {
		return JobBuilder.newJob(StationPumpW1HJob.class).withIdentity("stationPumpW1h").storeDurably().build();
	}

	@Bean
	public JobDetail PumpSum1hJobDetail() {
		return JobBuilder.newJob(PumpSum1HJob.class).withIdentity("pumpSum1h").storeDurably().build();
	}

	@Bean
	public JobDetail wiuSumW1hJobDetail() {
		return JobBuilder.newJob(WiuSumW1HJob.class).withIdentity("wiuSumW1h").storeDurably().build();
	}

	@Bean
	public JobDetail wiuW1hJobDetail() {
		return JobBuilder.newJob(WiuW1HJob.class).withIdentity("wiuW1h").storeDurably().build();
	}

	@Bean
	public JobDetail weather1HJobDetail(){
		return JobBuilder.newJob(Weather1HJob.class).withIdentity("weather1h").storeDurably().build();
	}

	@Bean
	public Trigger water1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(water1hJobDetail()).withIdentity("Water1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}
	@Bean
	public Trigger rain1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(rain1hJobDetail()).withIdentity("Rain1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}
	@Bean
	public Trigger flow1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(flow1hJobDetail()).withIdentity("Flow1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger stationFlow1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(stationFlow1hJobDetail()).withIdentity("StationFlow1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}
	@Bean
	public Trigger erhaiRun1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(erhaiRun1hJobDetail()).withIdentity("ErhaiRun1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
				//.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?")).build();
	}

	//水质每四个小时进行一次纳污能力计算
	@Bean
	public Trigger crep4HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(crep4hJobDetail()).withIdentity("crep4HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger stationPumpW1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(stationPumpW1hJobDetail()).withIdentity("sationPumpW1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger pumpSum1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(PumpSum1hJobDetail()).withIdentity("pumpSum1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger wiuSumW1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(wiuSumW1hJobDetail()).withIdentity("wiuSumW1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger wiuW1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(wiuW1hJobDetail()).withIdentity("wiuW1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}

//	@Bean
//	public Trigger weather1HJobTrigger(){
//		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
//				.repeatForever();
//		return TriggerBuilder.newTrigger().forJob(weather1HJobDetail()).withIdentity("weather1HJobTrigger")
//				.withSchedule(scheduleBuilder).build();
//	}
}