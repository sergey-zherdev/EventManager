package sheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

/**
 * Created by Сергей on 01.12.2016.
 */
public class ConsoleJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String description = context.getJobDetail().getDescription();
		System.out.println(description + " " + context.getTrigger().getStartTime());

	}
}
