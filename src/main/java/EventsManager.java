import controls.EventControl;
import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import storages.StorageList;

import static controls.EventControl.save;
import static controls.EventControl.setStorage;
import static controls.EventControl.viewAllEvents;

/**
 * Created by Сергей on 14.11.2016.
 */
public class EventsManager {
	public static void main(String[] args) {
		EventControl.setStorage(StorageList.CONTAINER);
		//EventControl.save(EventsFactory.getEvent(EventList.ALARM, new LocalDate(2016, 11, 01), new LocalTime(17, 15), "with repeat", true));
		EventControl.save(EventsFactory.getEvent(EventList.REMINDER, new LocalDate(2016, 11, 01), new LocalTime(17, 14), "without repeat", false));
	}
}
