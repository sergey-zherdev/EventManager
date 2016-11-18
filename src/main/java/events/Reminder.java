package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Reminder extends AbstractEvent{
	public Reminder(LocalDate date, LocalTime time, String description) {
		super(date, time, description);
	}

	public void run() {

	}

	public EventList getEventType() {
		return EventList.REMINDER;
	}
}
