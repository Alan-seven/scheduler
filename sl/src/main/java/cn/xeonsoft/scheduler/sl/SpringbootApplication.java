package cn.xeonsoft.scheduler.sl;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cn.xeonsoft.scheduler.sl.job.Every1HJob;

@MapperScan(basePackages = "cn.xeonsoft.scheduler.sl.respository")
@SpringBootApplication()
public class SpringbootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public JobDetail every1HJobDetail() {
		return JobBuilder.newJob(Every1HJob.class).withIdentity("every1h").storeDurably().build();
	}

	@Bean
	public Trigger every1HJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(every1HJobDetail()).withIdentity("Every1HJobTrigger")
				.withSchedule(scheduleBuilder).build();
	}
}