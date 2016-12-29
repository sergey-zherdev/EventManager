package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public interface Eventable {
    LocalTime getStartTime();

    LocalDate getStartDate();

    LocalTime getEndTime();

    LocalDate getEndDate();

    String getDescription();

    String getId();

    int getRepeatTime();

    boolean isConsole();

    boolean isMessage();

    boolean getRepeat();

    EventList getEventType();

    String getAddress();

}
