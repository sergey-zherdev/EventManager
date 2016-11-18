package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public abstract class AbstractEvent implements Eventable {
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

	public LocalTime getTime() {
		return time;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public boolean getRepeat() {
		return repeat;
	}
}
