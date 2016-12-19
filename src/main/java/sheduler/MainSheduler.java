package sheduler;

import events.Eventable;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;
import java.util.Calendar;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Сергей on 01.12.2016.
 */
public class MainSheduler {
    private Eventable event;

    public MainSheduler(Eventable event) {
        this.event = event;
        try {
            this.run();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void run() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = newJob(ConsoleJob.class)
                .withDescription(event.getDescription())
                .build();

        Trigger trigger = getTrigger();
        sched.scheduleJob(job, trigger);
        sched.start();

        //sched.shutdown(true);
    }

    private Trigger getTrigger() {
        if (event.getRepeat()) {
            return newTrigger()
                    .startAt(new Date(new Date().getYear(), event.getDate().getMonthOfYear(), event.getDate().getDayOfMonth(), event.getTime().getHourOfDay(), event.getTime().getMinuteOfHour()))
                    //.withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever())
                    .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();
        }
        return newTrigger()
                .startAt(new Date(new Date().getYear(), event.getDate().getMonthOfYear(), event.getDate().getDayOfMonth(), event.getTime().getHourOfDay(), event.getTime().getMinuteOfHour()))
                .build();
    }
}
