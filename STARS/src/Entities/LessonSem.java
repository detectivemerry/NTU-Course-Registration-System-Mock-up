package Entities;

import Enums.DayOfWeekStars;
import Enums.LessonType;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Represents the seminar lesson for an index of a Course
 * Implements Lesson and Serializable class
 */
public class LessonSem implements Lesson, Serializable {
    /*
    * Private instances of attributes of lessonsem
    */
    private LessonType lessonType = LessonType.LECTURE;
    private Venue semVenue;
    private LocalTime semStartTime;
    private LocalTime semEndTime;
    private DayOfWeekStars dayOfWeek;
    private String remark;

    /**
     * Creates a LessonSem Object
     * @param semVenue Venue of the seminar lesson
     * @param semStartTime  Start time of the seminar lesson
     * @param semEndTime End time of the seminar lesson
     * @param dayOfWeek Day of the week of the seminar lesson
     * @param remark Remark keyed in for the seminar lesson
     */
    public LessonSem(Venue semVenue, LocalTime semStartTime, LocalTime semEndTime, DayOfWeekStars dayOfWeek, String remark){
        this.semVenue = semVenue;
        this.semStartTime = semStartTime;
        this.semEndTime = semEndTime;
        this.dayOfWeek = dayOfWeek;
        this.remark = remark;
    }

    /**
     * Retrieves the venue for this lesson
     * @return venue for this lesson
     */
    @Override
    public Venue getVenue() {
        return semVenue;
    }

    /**
     * Retrieves the start time for this lesson
     * @return start time for this seminar lesson
     */
    @Override
    public LocalTime getStartTime() {
        return semStartTime;
    }

    /**
     * Retrieves the end time for this lesson
     * @return end time for this seminar lesson
     */
    @Override
    public LocalTime getEndTime() {
        return  semEndTime;
    }

    /**
     * Retrieves the lesson type of this lesson
     * @return the lesson type for this lesson
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
     * Retrieves the venue for this seminar lesson
     * @return venue for this seminar lesson
     */
    public Venue getSemVenue() {
        return semVenue;
    }

    /**
     * Modifies the start time for this seminar lesson
     * @param semStartTime The sem start time to be set as the new sem start time
     */
    public void setSemStartTime(LocalTime semStartTime) {
        this.semStartTime = semStartTime;
    }

    /**
     * Modifies the end time for this seminar lesson
     * @param semEndTime The sem end time to be set as the new sem end time
     */
    public void setSemEndTime(LocalTime semEndTime) {
        this.semEndTime = semEndTime;
    }
}
