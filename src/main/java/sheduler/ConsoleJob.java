package sheduler;

import controls.EventControl;
import events.Eventable;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.quartz.*;

import java.util.Date;

/**
 * Created by Сергей on 01.12.2016.
 */
public class ConsoleJob {

    static void work(Eventable event) {
        System.err.println(event.getDescription() + " " + new Date());
    }
}
