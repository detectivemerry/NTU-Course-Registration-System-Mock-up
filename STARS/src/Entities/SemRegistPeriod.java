package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the accessible registration period for students in STARS
 * Implements the Serializable class
 */
public class SemRegistPeriod implements Serializable {
    /**
     * Private instance attributes of SemRegistPeriod
     */
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a SemRegistPeriod Object
     * @param begin Time the semester registration begins
     * @param end Time the semester registratino ends
     */
    public SemRegistPeriod(LocalDateTime begin, LocalDateTime end){
        this.startDate = begin;
        this.endDate = end;
    }

    /**
     * Default constructor
     */
    public SemRegistPeriod(){
    }

    /**
     * Retrieves the starting date and time
     * @return startDate
     */
    public LocalDateTime getStartDateTime(){
        return startDate;
    }

    /**
     * Retrieves the ending date and time
     * @return endDate
     */
    public LocalDateTime getEndDateTime(){
        return endDate;
    }

    /**
     * Modifies the starting date and time for registration
     * @param newDateTime New date time to be set into the semester registration period
     */
    public void setStartDate(LocalDateTime newDateTime){
        this.startDate = newDateTime;
    
    /**
     * Modifies the ending date and time for registration
     * @param newDateTime
     */
    }
    public void setEndDate(LocalDateTime newDateTime){
        this.endDate = newDateTime;
    }

    /**
     * Retrieves the starting date and time
     * @return startDate
     */
    public LocalDateTime getBegin() {
        return startDate;
    }

    /**
     * Retrieves the ending date and time
     * @return endDate
     */
    public LocalDateTime getEnd(){return endDate;}
    
    /**
     * Creates a copy of current SemRegistPeriod object
     * @return the SemRegistPeriod object
     */
    public SemRegistPeriod copyObject(){
        return this;
    }

    /**
     * convert to string
     */
    @Override
    public String toString() {
        return "SemRegistPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
