package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.Date;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Reminder extends AbstractEvent {
    public Reminder(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description, boolean repeat, int repeatTime, boolean console, boolean message, String address) {
        super(id, startDate, startTime, endDate, endTime, description, repeat, repeatTime, console, message, address);
    }

    public EventList getEventType() {
        return EventList.REMINDER;
    }
}
