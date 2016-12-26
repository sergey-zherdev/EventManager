package controls;

import events.EventList;
import events.Eventable;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import sheduler.MainSheduler;
import storages.Base;
import storages.Savable;
import storages.StorageFactory;
import storages.StorageList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Сергей on 09.11.2016.
 */
public class EventControl {
    private static final Logger log = Logger.getLogger(EventControl.class);

    private static Savable storage;

    public static void setStorage(StorageList storage) {
        EventControl.storage = StorageFactory.getStorage(storage);
        log.info("Storage is " + storage + " now");
    }

    public static void save(Eventable event) {
        log.info("Event with id = " + event.getId() + " created");
        loadShedule(event);
        storage.save(event);

    }

    public static HashMap<String, String> getAllEvents() {
        return storage.watchAll();
    }

    private static void loadShedule(Eventable event) {
        new MainSheduler(event);
    }

    public static void setup() {
        log.info("Start uploading events");
        Map<String, Eventable> map = Base.getEventsMap();

        for (Map.Entry<String, Eventable> entry : map.entrySet()) {
            Eventable event = entry.getValue();
            if ("work".equals(Base.getState(event)))
                if (event.getEndDate().compareTo(new LocalDate()) >= 0 && event.getEndTime().compareTo(new LocalTime()) >= 0)
                    loadShedule(event);
                else
                    toArchive(event);
        }
        log.info("Events uploaded successfully");
    }


    public static void toArchive(Eventable event) {
        storage.toArchive(event);
        log.info("Event with id = " + event.getId() + " change state to \"archive\"");
    }
}
