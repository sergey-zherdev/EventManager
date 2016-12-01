package controls;

import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import sheduler.MainSheduler;
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

	public static void save(Eventable event) {
		new MainSheduler(event);
		storage.save(event);
	}
	public static void viewAllEvents(){
		storage.watchAll();
	}

}
