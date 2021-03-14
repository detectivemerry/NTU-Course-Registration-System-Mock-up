package Entities;
import Enums.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents lab lesson for an index of a Course
 * Implements Lesson and Serializable class
 */
public class LessonLab implements Lesson, Serializable {
    private LessonType lessonType = LessonType.LAB;
    private Venue labVenue;
    private LocalTime labStartTime;
    private LocalTime labEndTime;
    private DayOfWeekStars dayOfWeek;
    private String labAssistant;
    private String remark;
    /**
     * Creates a LessonLab Object
     * @param lecVenue Venue of the lecture lesson
     * @param lecStartTime Start time of the lecture lesson
     * @param lecEndTime End time of the lecture lesson
     * @param dayOfWeek Day of the week of the lecture lesson
     * @param remark Remark keyed in for the lecture lesson
     */
    public LessonLab(Venue lecVenue, LocalTime lecStartTime, LocalTime lecEndTime, DayOfWeekStars dayOfWeek, String remark){
        this.labVenue = lecVenue;
        this.labStartTime = lecStartTime;
        this.labEndTime = lecEndTime;
        this.dayOfWeek = dayOfWeek;
        this.remark = remark;
    }

    /**
     * Retrieves the venue for this lesson
     * @return venue for this lesson
     */
    public Venue getVenue() {
        return labVenue;
    }

    /**
     * Retrieves the start time for this lesson
     * @return start time for this lab lesson
     */
    @Override
    public LocalTime getStartTime() {
        return labStartTime;
    }

    /**
     * Retrieves the end time for this lesson 
     * @return end time for this lab lesson
     */
    @Override
    public LocalTime getEndTime() {
        return labEndTime;
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
        return "LessonLab{" +
                "lessonType=" + lessonType +
                ", labVenue=" + labVenue +
                ", labStartTime=" + labStartTime +
                ", labEndTime=" + labEndTime +
                ", dayOfWeek=" + dayOfWeek +
                ", labAssistant='" + labAssistant + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * Retrieve the lab assistant for this lab lesson
     * @return Lab assistant of this lab lesson
     */
    public String getLabAssistant() {
        return labAssistant;
    }

    /**
     * Modifies the venue for this lab lesson
     * @param labVenue Lab venue of the lesson
     */
    public void setLabVenue(Venue labVenue) {
        this.labVenue = labVenue;
    }

    /**
     * Modifies the start time for this lab lesson
     * @param labStartTime Start time for this lab lesson
     */
    public void setLabStartTime(LocalTime labStartTime) {
        this.labStartTime = labStartTime;
    }

    /**
     * Modifies the end time for this lab lesson
     * @param labEndTime End time for this lab lesson
     */
    public void setLabEndTime(LocalTime labEndTime) {
        this.labEndTime = labEndTime;
    }

    /**
     * Modifies the lab assistant for this lab lesson
     * @param labAssistant Lab assistant for this lab lesson
     */
    public void setLabAssistant(String labAssistant) {
        this.labAssistant = labAssistant;
    }
}
