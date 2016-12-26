package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventsFactory {
    public static Eventable getEvent(String id, EventList eventList, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description, boolean repeat) {
        switch (eventList) {
            case ALARM:
                return new Alarm(id, startDate, startTime, endDate, endTime, repeat);
            case REMINDER:
                return new Reminder(id, startDate, startTime, endDate, endTime, description);
            default:
                return null;
        }
    }

}
