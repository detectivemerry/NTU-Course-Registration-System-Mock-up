package Entities;
import Enums.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents lecture lesson for an index of a Course
 * Implements Lesson and Serializable class
 */
public class LessonLec implements Lesson, Serializable {

    private LessonType lessonType = LessonType.LECTURE;
    private Venue lecVenue;
    private LocalTime lecStartTime;
    private LocalTime lecEndTime;
    private DayOfWeekStars dayOfWeek;
    private String remark;

    /**
     * Creates a LessonLec Object
     * @param lecVenue Venue of the lecture lesson
     * @param lecStartTime Start time of the lecture lesson
     * @param lecEndTime End time of the lecture lesson
     * @param dayOfWeek Day of the week of the lecture lesson
     * @param remark Remark keyed in for the lecture lesson
     */
    public LessonLec(Venue lecVenue, LocalTime lecStartTime, LocalTime lecEndTime, DayOfWeekStars dayOfWeek, String remark){
        this.lecVenue = lecVenue;
        this.lecStartTime = lecStartTime;
        this.lecEndTime = lecEndTime;
        this.dayOfWeek = dayOfWeek;
        this.remark = remark;
    }

    /**
     * Retrieves the venue for this lesson
     * @return venue for this lesson
     */
    @Override
    public Venue getVenue() {
        return lecVenue;
    }
    /**
     * Retrieves the start time for this lesson
     * @return start time for this lecture lesson
     */
    @Override
    public LocalTime getStartTime() {
        return lecStartTime;
    }

    /**
     * Retrieves the end time for this lesson 
     * @return end time for this lecture lesson
     */
    @Override
    public LocalTime getEndTime() {
        return lecEndTime;
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
     * Retrieves the remark input for this lesson
     * @return remark input for this lesson
     */
    @Override
    public String getRemark() {
        return remark;
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
        return "LessonLec{" +
                "lessonType=" + lessonType +
                ", lecVenue=" + lecVenue +
                ", lecStartTime=" + lecStartTime +
                ", lecEndTime=" + lecEndTime +
                ", dayOfWeek=" + dayOfWeek +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * Modifies the venue for this lecture lesson
     * @param lecVenue Lec venue of the lesson
     */
    public void setLecVenue(Venue lecVenue) {
        this.lecVenue = lecVenue;
    }

    /**
     * Modifies the start time for this lecture lesson
     * @param lecStartTime Start time for this lecture lesson
     */
    public void setLecStartTime(LocalTime lecStartTime) {
        this.lecStartTime = lecStartTime;
    }

    /**
     * Modifies the end time for this lecture lesson
     * @param lecEndTime End time for this lecture lesson
     */
    public void setLecEndTime(LocalTime lecEndTime) {
        this.lecEndTime = lecEndTime;
    }

}
