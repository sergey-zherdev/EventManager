import controls.EventControl;
import events.EventList;
import events.Eventable;
import events.EventsFactory;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import storages.StorageList;

import java.util.UUID;


/**
 * Created by Сергей on 14.11.2016.
 */
public class EventsManager {
    private static final Logger log = Logger.getLogger(EventsManager.class);
    public static void main(String[] args) {
        EventControl.setStorage(StorageList.BASE);
        //EventControl.setup();
        Eventable eventable = EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), EventList.ALARM, new LocalDate(), new LocalTime().plusMinutes(1), new LocalDate(), new LocalTime().plusMinutes(2), "Hello", true, 10, true, true, "sergey.zherdev.94@mail.ru");
        EventControl.save(eventable);
    }
}
