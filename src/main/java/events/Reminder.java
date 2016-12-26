package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Reminder extends AbstractEvent {
    public Reminder(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description) {
        super(id, startDate, startTime, endDate, endTime, description);
    }

    public void run() {

    }

    public EventList getEventType() {
        return EventList.REMINDER;
    }
}
