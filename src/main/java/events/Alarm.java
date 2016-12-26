package events;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Alarm extends AbstractEvent {

    public Alarm(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, boolean repeat) {
        super(id, startDate, startTime, endDate, endTime, repeat);
        description = "ALARM!!!";
    }

    public void run() {

    }

    public EventList getEventType() {
        return EventList.ALARM;
    }

}
