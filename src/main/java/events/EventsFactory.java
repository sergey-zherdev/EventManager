package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventsFactory {
    public static Eventable getEvent(String id, EventList eventList, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description, boolean repeat, int repeatTime, boolean console, boolean message, String adress) {
        switch (eventList) {
            case ALARM:
                return new Alarm(id, startDate, startTime, endDate, endTime, description, repeat, repeatTime, console, message, adress);

            case REMINDER:
                return new Reminder(id, startDate, startTime, endDate, endTime, description, repeat, repeatTime, console, message, adress);
            default:
                return null;
        }
    }

}
