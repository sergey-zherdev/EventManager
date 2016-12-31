package sheduler;

import controls.EventControl;
import events.Eventable;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by Сергей on 29.12.2016.
 */
public class MainJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();
        Eventable event = (Eventable) map.get("event");
        if (event.getRepeat()) {
            if (context.getScheduledFireTime().getSeconds() == new Date().getSeconds())
                work(event);
            if (context.getNextFireTime() == null)
                EventControl.toArchive(event);

        } else {
            if (event.getStartDate().compareTo(new LocalDate()) == 0) {
                if (event.getStartTime().withSecondOfMinute(0).withMillisOfSecond(0).compareTo(new LocalTime().withSecondOfMinute(0).withMillisOfSecond(0)) == 0) {   //поправка на задержку до 1 минуты
                    work(event);
                    EventControl.toArchive(event);
                }
            } else {
                EventControl.toArchive(event);
            }
        }
    }
    void work(Eventable event){
        if(event.isConsole())
            ConsoleJob.work(event);
        if(event.isMessage())
            MessageJob.work(event);

    }
}
