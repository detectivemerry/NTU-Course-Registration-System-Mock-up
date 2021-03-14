package Entities;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Represents an index of a course registered in STARS.
 * Each index from the same Course can have different attributes
 * Implements the Serializable class
 */
public class Index implements Serializable {
    /**
     * Private instance attributes of index
     */
    private String indexID;
    private int maxVacancy;
    private ArrayList<Lesson> lessonList;
    private String groupName;
    private ArrayList<String> studentsRegistered;
    private ArrayList<String> studentsInWaitLists;
    private ArrayList<String> studentsExempted;
    public Index(){
    }
    /**
     * Creates an Index Object
     * @param indexID Unique identifier for index, has 5 integers
     * @param maxVacancy Maximum vacancy for the index
     * @param lessonList List of lessons for the index
     * @param groupName Group name of the index 
     */
    public Index(String indexID, int maxVacancy, ArrayList<Lesson> lessonList, String groupName){
        this.indexID = indexID;
        this. maxVacancy = maxVacancy;
        this.lessonList = lessonList;
        this.groupName = groupName;
        studentsRegistered = new ArrayList<String>();
        studentsInWaitLists = new ArrayList<String>();
        studentsExempted = new ArrayList<String>();
    }
    /**
     * Retrieves the index id 
     * @return Index id of index
     */
    public String getIndexID() {
        return indexID;
    }

    /**
     * Retrieves maximum vacancy of index
     * @return max vacancy of index
     */
    public int getMaxVacancy() {
        return maxVacancy;
    }

    /**
     * retrieves the group name
     * @return group name of index
     */
    public String getGroupName(){return groupName;}

    /**
     * retrieves the list of lessons 
     * @return Array list of lessons in the index
     */
    public ArrayList<Lesson> getLessonList() {
        return lessonList;
    }

    /**
     * retrieves the students registered
     * @return Array list of students registered for this index
     */
    public ArrayList<String> getStudentsRegistered(){return studentsRegistered;}

    /**
     * retrieves the students in waitlist
     * @return Array list of students in the waitlist for this index
     */
    public ArrayList<String> getStudentsInWaitLists() {
        return studentsInWaitLists;
    }

    /**
     * retrieves the exempted students
     * @return Array list of students exempted
     */
    public ArrayList<String> getStudentsExempted() {
        return studentsExempted;
    }

    /**
     * Modifies the registered students for this index
     * @param studentList list of matric number of students to be set into the course object
     */
    public void setStudentsRegistered(ArrayList<String> studentList){this.studentsRegistered = studentList;}

    /**
     * Modifies the students in waitlist for this index
     * @param studentsInWaitLists list of matric number of students to be set into the course object
     */
    public void setStudentsInWaitLists(ArrayList<String> studentsInWaitLists) {
        this.studentsInWaitLists = studentsInWaitLists;
    }

    /**
     * Modifies the exempted students for this index
     * @param studentsExempted list of matric number of students to be set into the course object
     */
    public void setStudentsExempted(ArrayList<String> studentsExempted) {
        this.studentsExempted = studentsExempted;
    }

    /**
     * Modifies the index id 
     * @param indexID Index ID of index to be set in the Index object
     */
    public void setIndexID(String indexID) {
        this.indexID = indexID;
    }

    /**
     * Modifies the maximum vacancy for this index
     * @param maxVacancy Max vacancy of index to be set in the Index object
     */
    public void setMaxVacancy(int maxVacancy) {
        this.maxVacancy = maxVacancy;
    }

    /**
     * Modifies the list of lessons for this index
     * @param lessonList list of lessons to be set into the Index object
     */
    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    /**
     * Modifies the group name for this index
     * @param groupName group name of Index to be set into Index object
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Creates a copy of current index
     * @return an exact copy of this object
     */
    public Index copyObject(){
        return this;
    }
}
