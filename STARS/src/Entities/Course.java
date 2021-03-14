package Entities;
import Enums.CourseType;
import Enums.School;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a course registered in STARS.
 * A Course can contain multiple Indexes.
 * Implements the Serializable class
 */
public class Course implements Serializable {
    /*
    * Private instance attributes of course
    */
    private String courseCode;
    private String courseName;
    private int[] courseSem;
    private int courseAU;
    private ArrayList<Index> indexes;
    private School school;
    private ArrayList<CourseType> courseType;

    /**
     * Default constructor
     */
    public Course(){
    }
    /**
     * Creates a Course Object
     * @param courseCode Unique identifier for course, has 6 characters
     * @param courseName Name of course
     * @param courseSem Semester that course is available
     * @param courseAU Credit of course
     * @param school School of course
     * @param courseType Type of course
     * @see School
     * @see CourseType
     */
    public Course(String courseCode, String courseName, int[] courseSem, int courseAU, School school, ArrayList<CourseType> courseType){
        this.courseCode = courseCode;
        this.courseName =courseName;
        this.courseSem = courseSem;
        this.courseAU = courseAU;
        this.school = school;
        this.courseType = courseType;
        this.indexes = new ArrayList<Index>();
    }
    /**
     * Retrieves the indexes of course
     * @return The list of indexes of course
     */
    public ArrayList<Index> getIndexes() {
        return indexes;
    }

    /**
     * Retrieves the course code
     * @return Course code of course
     */
    public String getCourseCode() {
        return courseCode;
    }

     /**
     * Retrieves the course name
     * @return Course name of course
     */
    public String getCourseName() {
        return courseName;
    }

     /**
     * Retrieves the course sem
     * @return list of semesters that course is available in
     */
    public int[] getCourseSem() {
        return courseSem;
    }

     /**
     * Retrieves the course school
     * @return School enum that course is allocated in
     */
    public School getSchool() {
        return school;
    }
     /**
     * Retrieves the course type
     * @return Array list of course types allocated to course
     */
    public ArrayList<CourseType> getCourseType(){return courseType;}
     /**
     * Retrieves the course AU credits
     * @return AU credits of course
     */
    public int getCourseAU(){return courseAU;}
     /**
     * Modifies the course code
      * @param courseCode The new course code to be set into the Course object
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    /**
     * Modifies the course name
     *@param courseName The new course name to be set into the Course object
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Modifies the course Index
     *@param indexes The new course set of indexes to be set into the Course object
     */
    public void setIndexes(ArrayList<Index> indexes) {
        this.indexes = indexes;
    }
    /**
     * Modifies the course semester
     * @param courseSem The new list of course sem to be set into the Course object
     */
    public void setCourseSem(int[] courseSem) {
        this.courseSem = courseSem;
    }
     /**
     * Modifies the course school
      * @param school The new School object to be set into the School object
     */
    public void setSchool(School school) {
        this.school = school;
    }
     /**
     * Checks if current course is not null
     * @return boolean whether the course object is a null object or not
     */
    public boolean isValid(){
        return courseCode != null && courseName !=null;
    }
     /**
     * Creates a copy of current course
     * @return the course object
     */
    public Course copyObject(){
        return this;
    }
}
