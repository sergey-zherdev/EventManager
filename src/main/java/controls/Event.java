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
	private EventList eventType;
	private boolean repeat;

	public void setEventType(EventList event) {
		eventType = event;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getDescription() {
		return description;
	}

	public EventList getEventType() {
		return eventType;
	}

	public boolean isRepeat() {
		return repeat;
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

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean getRepeat() {
		return repeat;
	}
}
