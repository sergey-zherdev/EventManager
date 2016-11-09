package events;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventsFactory {
	public static Eventable getEvent(EventList eventList) {
		switch (eventList) {
			case ALARM: return new Alarm();
			case REMINDER: return new Reminder();
			default: return null;
		}
	}
}
