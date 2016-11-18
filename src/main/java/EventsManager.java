import controls.Event;
import events.EventList;
import storages.Base;
import storages.StorageList;

import static controls.EventControl.save;
import static controls.EventControl.setStorage;
import static controls.EventControl.viewAllEvents;

/**
 * Created by Сергей on 14.11.2016.
 */
public class EventsManager {
	public static void main(String[] args) {
		Event event = new Event();
		event.setDay(2016,11,15);
		event.setTime(12,00);
		event.setEventType(EventList.REMINDER);
		event.setDescription("Остался месяц а ты нифига не сделал!");
		event.setRepeat(false);
		setStorage(StorageList.BASE);
		/*save(event);
		event.setDay(2016, 12, 31);
		event.setTime(23,59);
		event.setDescription("1 minute to NY");
		save(event);*/
		viewAllEvents();
	}
}
