package sheduler;

import events.Eventable;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Сергей on 01.12.2016.
 */
public class MainSheduler {
    private static final Logger log = Logger.getLogger(MainSheduler.class);
    private Eventable event;

    public MainSheduler(Eventable event) {
        this.event = event;
        try {
            this.run();
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    private void run() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

            JobDetail job = newJob(MainJob.class)
                    .withDescription(event.getDescription())
                    .build();
            job.getJobDataMap().put("event", event);
            sched.scheduleJob(job, getTrigger(event));

        sched.start();


        //sched.shutdown(true);
    }

    private Trigger getTrigger(Eventable event) { //триггер считает месяцы с 0 по 11, поэтому идёт уменьшение месяца на 1
        if (event.getRepeat()) {
            return newTrigger()
                    .startAt(new Date(new Date().getYear(), event.getStartDate().minusMonths(1).getMonthOfYear(), event.getStartDate().getDayOfMonth(), event.getStartTime().getHourOfDay(), event.getStartTime().getMinuteOfHour()))
                    //.withSchedule(simpleSchedule().withIntervalInSeconds(event.getRepeatTime()).repeatForever())
                    .withSchedule(simpleSchedule().withIntervalInHours(event.getRepeatTime()).repeatForever())
                    .endAt(new Date(new Date().getYear(), event.getEndDate().minusMonths(1).getMonthOfYear(), event.getEndDate().getDayOfMonth(), event.getEndTime().getHourOfDay(), event.getEndTime().getMinuteOfHour()))
                    .build();
        }
        return newTrigger()
                .startAt(new Date(new Date().getYear(), event.getStartDate().minusMonths(1).getMonthOfYear(), event.getStartDate().getDayOfMonth(), event.getStartTime().getHourOfDay(), event.getStartTime().getMinuteOfHour()))
                //.endAt(new Date(new Date().getYear(), event.getStartDate().getMonthOfYear(), event.getStartDate().getDayOfMonth(), event.getStartTime().getHourOfDay(), event.getStartTime().getMinuteOfHour()))
                .build();
    }
}
