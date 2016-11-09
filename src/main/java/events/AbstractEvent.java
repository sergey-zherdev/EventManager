package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public abstract class AbstractEvent {
	LocalDate date;
	LocalTime time;
	String description;
	boolean repeat;

	public AbstractEvent(LocalDate date, LocalTime time, boolean repeat){
		this.date = date;
		this.time = time;
		this.repeat = repeat;
	}

	public AbstractEvent(LocalDate date, LocalTime time, String description) {
		this.date = date;
		this.time = time;
		this.description = description;
	}
}
