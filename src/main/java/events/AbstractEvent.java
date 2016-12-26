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

    public AbstractEvent(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, boolean repeat) {
        this.startDate = startDate;
        this.id = id;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.repeat = repeat;
    }

    public AbstractEvent(String id, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String description) {
        this.startDate = startDate;
        this.id = id;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.description = description;
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

    public boolean getRepeat() {
        return repeat;
    }
}
