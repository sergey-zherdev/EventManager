package controls;

import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Event {
	private LocalDate date;
	private LocalTime time;
	private String description;
	private Eventable event;

	public void setEvent(EventList event) {
		this.event = EventsFactory.getEvent(event);
	}

	public void setTime(int hour, int min) {
		time = new LocalTime(hour, min);
	}

	public void setDay(int year, int month, int day) {
		date = new LocalDate(year, month, day);
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
