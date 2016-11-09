package controls;

import events.EventList;
import events.EventsFactory;
import storages.Savable;
import storages.StorageFactory;
import storages.StorageList;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventControl {
	private static Savable storage;
	private static Event event;

	public static void setStorage(StorageList storage) {
		EventControl.storage = StorageFactory.getStorage(storage);
	}

	public static void save(Event event) {
		storage.save(EventsFactory.getEvent(event.getEventType(), event.getDate(), event.getTime(), event.getDescription(), event.getRepeat()));
	}

	public static void main(String[] args) {
		Event event = new Event();
		event.setDay(2016,11,15);
		event.setTime(12,00);
		event.setEventType(EventList.ALARM);
		event.setDescription("Остался месяц а ты нифига не сделал!");
		event.setRepeat(false);
		setStorage(StorageList.CONTAINER);
		save(event);
	}
}
