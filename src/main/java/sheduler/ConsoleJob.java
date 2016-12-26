package sheduler;

import controls.EventControl;
import events.Eventable;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.quartz.*;

import java.util.Date;

/**
 * Created by Сергей on 01.12.2016.
 */
public class ConsoleJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();
        Eventable event = (Eventable) map.get("event");
        if (event.getRepeat()) {
            if (context.getScheduledFireTime().getSeconds() == new Date().getSeconds())
                work(context);
            if (context.getNextFireTime() == null)
                EventControl.toArchive(event);

        } else {
            if (event.getStartDate().compareTo(new LocalDate()) == 0) {
                if (event.getStartTime().withSecondOfMinute(0).withMillisOfSecond(0).compareTo(new LocalTime().withSecondOfMinute(0).withMillisOfSecond(0)) == 0) {   //поправка на задержку до 1 минуты
                    work(context);
                    EventControl.toArchive(event);
                }
            } else {
                EventControl.toArchive(event);
            }
        }
    }

    private void work(JobExecutionContext context) {
        String description = context.getJobDetail().getDescription();
        System.err.println(description + " " + new Date());
    }
}
