package Entities;
import Enums.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents tutorial lesson for an index of a Course
 * Implements Lesson and Serializable class
 */
public class LessonTut implements Lesson, Serializable {
    private LessonType lessonType = LessonType.TUTORIAL;
    private Venue tutVenue;
    private LocalTime tutStartTime;
    private LocalTime tutEndTime;
    private DayOfWeekStars dayOfWeek;
    private String tutorName;
    private String remark;

    /**
     * Creates a LessonTut Object
     * @param tutVenue Venue of the tutorial lesson
     * @param tutStartTime Start time of the tutorial lesson
     * @param tutEndTime End time of the tutorial lesson
     * @param dayOfWeek Day of the week of the tutorial lesson
     * @param remark Remark keyed in for the tutorial lesson
     */
    public LessonTut(Venue tutVenue, LocalTime tutStartTime, LocalTime tutEndTime, DayOfWeekStars dayOfWeek, String remark){
        this.tutVenue = tutVenue;
        this.tutStartTime = tutStartTime;
        this.tutEndTime = tutEndTime;
        this.dayOfWeek = dayOfWeek;
        this.remark = remark;
    }

    /**
     * Retrieves the venue for this lesson
     * @return venue for this lesson
     */
    @Override
    public Venue getVenue() {
        return tutVenue;
    }

    /**
     * Retrieves the start time for this lesson
     * @return start time for this tutorial lesson
     */
    @Override
    public LocalTime getStartTime() {
        return tutStartTime;
    }

    /**
     * Retrieves the end time for this lesson 
     * @return end time for this tutorial lesson
     */
    @Override
    public LocalTime getEndTime() {
        return tutEndTime;
    }

    /**
     * Retrieves the remark input for this lesson
     * @return remark input for this lesson
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * Retrieves the type of this lesson
     * @return lesson type for this lesson
     */
    @Override
    public LessonType getLessonType() {
        return lessonType;
    }

    /**
     * Retrieves the day of the week for this lesson
     * @return day of the week for this lesson
     */
    @Override
    public DayOfWeekStars getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * convert to string
     */
    @Override
    public String toString() {
        return "LessonTut{" +
                "lessonType=" + lessonType +
                ", tutVenue=" + tutVenue +
                ", tutStartTime=" + tutStartTime +
                ", tutEndTime=" + tutEndTime +
                ", dayOfWeek=" + dayOfWeek +
                ", tutorName='" + tutorName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * Retrieve the tutor name for this tutorial lesson
     * @return Lab assistant of this lab lesson
     */
    public String getTutorName() {
        return tutorName;
    }

    /**
     * Modifies the venue for this tutorial lesson
     * @param tutVenue New venue to be set into this lesson object
     */
    public void setTutVenue(Venue tutVenue) {
        this.tutVenue = tutVenue;
    }

    /**
     * Modifies the start time for this tutorial lesson
     * @param tutStartTime Start time for this tutorial lesson
     */    
    public void setTutStartTime(LocalTime tutStartTime) {
        this.tutStartTime = tutStartTime;
    }

    /**
     * Modifies the end time for this tutorial lesson
     * @param tutEndTime End time for this tutorial lesson
     */
    public void setTutEndTime(LocalTime tutEndTime) {
        this.tutEndTime = tutEndTime;
    }

    /**
     * Modifies the tutor name for this tutorial lesson
     * @param tutorName Lab assistant for this tutorial lesson
     */
    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
}
