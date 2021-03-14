package Entities;
import Enums.*;
import java.time.LocalTime;
/**
 * Interface for all lesson types 
 */
public interface Lesson {
    /**
     * Retrieves the venue of lesson
     * @return venue of lesson
     */
    Venue getVenue();
    /**
     * Retrieves the start time of lesson
     * @return start time of lesson
     */
    LocalTime getStartTime();
    /**
     * Retrieves the end time of lesson
     * @return end time of lesson
     */
    LocalTime getEndTime();
    /**
     * Retrieves the lesson type of lesson
     * @return lesson type of lesson
     */
    LessonType getLessonType();
    /**
     * Retrieves the remarks of lesson
     * @return remark of lesson
     */
    String getRemark();
    /**
     * Retrieves the day of week of lesson
     * @return day of week of lesson
     */
    DayOfWeekStars getDayOfWeek();

}
