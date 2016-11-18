package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public interface Eventable {
	void run();
	LocalTime getTime();
	LocalDate getDate();
	String getDescription();

	boolean getRepeat();

	EventList getEventType();
}
