package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public class Alarm extends AbstractEvent implements Eventable {

	public Alarm(LocalDate date, LocalTime time, boolean repeat) {
		super(date, time, repeat);
	}

	public void run() {

	}
}
