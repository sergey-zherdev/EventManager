package controls;

import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import storages.Savable;
import storages.StorageFactory;
import storages.StorageList;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventControl {
	private static Savable storage;
	private static LocalDate date;
	private static LocalTime time;
	private static String description;
	private static EventList eventType;
	private static boolean repeat;

	public static void setStorage(StorageList storage) {
		EventControl.storage = StorageFactory.getStorage(storage);
	}

	public static void save() {
		storage.save(EventsFactory.getEvent(eventType, date, time, description, repeat));
	}
	public static void viewAllEvents(){
		storage.watchAll();
	}

	public void setEventType(EventList event) {
		eventType = event;
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
}
