package controls;

import events.EventList;
import storages.Savable;
import storages.StorageFactory;
import storages.StorageList;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventControl {
	private Savable storage;
	private Event event;

	public void setStorage(StorageList storage) {
		this.storage = StorageFactory.getStorage(storage);
	}

	public void save(Event event) {
		storage.save(event);
	}
}
