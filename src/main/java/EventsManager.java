import controls.EventControl;
import events.EventList;
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
        EventControl.setup();
        String id = String.valueOf(UUID.randomUUID());
         //EventControl.save(EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), EventList.ALARM, new LocalDate().minusMonths(20), new LocalTime(), new LocalDate().minusMonths(2), new LocalTime().plusMinutes(1), "qwe12", true));
        //EventControl.save(EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), EventList.ALARM, new LocalDate(), new LocalTime().plusMinutes(5), new LocalDate(), new LocalTime().plusMinutes(6), "qwe124", true));
        //EventControl.save(EventsFactory.getEvent(String.valueOf(UUID.randomUUID()), EventList.ALARM, new LocalDate(), new LocalTime().minusMinutes(10), new LocalDate(), new LocalTime().plusMinutes(6), "qwe124", false));
        //EventControl.getAllEvents();
    }
}
