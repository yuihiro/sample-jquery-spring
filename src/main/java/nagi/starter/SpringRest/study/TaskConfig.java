package nagi.starter.SpringRest.study;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Configuration
//@EnableScheduling
public class TaskConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(scheduer());
		taskRegistrar.addFixedRateTask(new Runnable() {

			@Override
			public void run() {
				processor().task();
			}
		}, 1000);
	}

	@Bean
	public ThreadPoolTaskScheduler scheduer() {
		ThreadPoolTaskScheduler task = new ThreadPoolTaskScheduler();
		task.setPoolSize(10);
		task.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		return task;
	}

	@Bean
	public SimpleProcessor processor() {
		return new SimpleProcessor();
	}

}
