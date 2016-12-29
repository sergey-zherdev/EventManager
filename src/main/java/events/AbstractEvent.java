package events;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Сергей on 09.11.2016.
 */
public abstract class AbstractEvent implements Eventable {
    LocalDate startDate;
    LocalTime startTime;
    LocalDate endDate;
    LocalTime endTime;
    String description;
    String id;
    boolean repeat;
    boolean console;
    boolean message;
    int repeatTime;
    String address;

    public AbstractEvent(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description, boolean repeat, int repeatTime, boolean console, boolean message, String address) {
        this.startDate = startDate;
        this.id = id;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
        this.console = console;
        this.message = message;
        this.repeatTime = repeatTime;
        this.repeat = repeat;
        this.address = address;
    }

    @Override


    public String getId() {
        return id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isConsole() {
        return console;
    }

    public boolean isMessage() {
        return message;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public boolean getRepeat() {
        return repeat;
    }

    public String getAddress() {
        return address;
    }
}
