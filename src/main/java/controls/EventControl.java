package controls;

import events.Eventable;
import events.EventsFactory;
import storages.Savable;
import storages.StorageFactory;
import storages.StorageList;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventControl {
	private static Savable storage;

	public static void setStorage(StorageList storage) {
		EventControl.storage = StorageFactory.getStorage(storage);
	}

	public static void save(Event event) {
		storage.save(EventsFactory.getEvent(event.getEventType(), event.getDate(), event.getTime(), event.getDescription(), event.getRepeat()));
	}
	public static void viewAllEvents(){
		storage.watchAll();
	}
}
