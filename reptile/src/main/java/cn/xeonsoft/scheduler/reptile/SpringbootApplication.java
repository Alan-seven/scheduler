package cn.xeonsoft.scheduler.reptile;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cn.xeonsoft.scheduler.reptile.weather.job.Every4HJob;

@MapperScan(basePackages = "cn.xeonsoft.scheduler.reptile.weather.respository")
@SpringBootApplication()
public class SpringbootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public JobDetail every4HJobDetail() {
		return JobBuilder.newJob(Every4HJob.class).withIdentity("every4h").storeDurably().build();
	}

	@Bean
	public Trigger every4HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(2)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(every4HJobDetail()).withIdentity("Every4HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}
}