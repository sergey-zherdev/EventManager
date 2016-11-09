package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventsFactory {
	public static Eventable getEvent(EventList eventList, LocalDate date, LocalTime time, String description, boolean repeat) {
		switch (eventList) {
			case ALARM: return new Alarm(date, time, repeat);
			case REMINDER: return new Reminder(date, time, description);
			default: return null;
		}
	}

}
