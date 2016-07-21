package nagi.starter.SpringRest.study;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class QuartzJob extends QuartzJobBean {

	public static final String COUNT = "count";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		int cnt = dataMap.getInt(COUNT);
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("QuartzJob " + jobKey + ": " + dataMap.get("name") + ": " + cnt);
		cnt++;
		dataMap.put(COUNT, cnt);
	}
}
