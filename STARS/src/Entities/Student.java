package Entities;
import Enums.*;
import java.io.Serializable;

/**
 * Represents the student user
 * Extends User and implements Serializable
 */
public class Student extends User implements Serializable {
    /*
    * Private instance attributes of student user
    */
    private String matricNumber;
    private int studyYear;
    private School school;
    private Program program;
    private int maxAUSem;
    /**
     * Default constructor
     */
    public Student(){
        super();
    }

    /**
     * Constructor for student login
     * @param username Username of the login credential
     * @param pwd Password of the login credential
     */
    public Student(String username, char[] pwd){
super(username,pwd);
    }

    /**
     * Constructor for student without email
     * @param username Username of student
     * @param pwd Password of student
     * @param name Name of student
     * @param nationality Nationality of student
     * @param gender Gender of student
     * @param matricNumber Matriculation number of student
     * @param studyYear Study year of student
     * @param program Program of student
     */
    public Student(String username, char[] pwd, String name, Nationality nationality, Gender gender,
                   String matricNumber, int studyYear, Program program ){

        super(username, pwd, name,nationality, gender);
        this.matricNumber = matricNumber;
        this.studyYear = studyYear;
        this.program = program;
    }

    /**
     * Constructor for all details of student (including email)
     * @param username Username of student
     * @param pwd Password of student
     * @param name Name of student
     * @param nationality Nationality of student
     * @param gender Gender of student
     * @param matricNumber Matriculation number of student
     * @param studyYear Study year of student
     * @param program Program of student
     * @param email Email of student
     */
    public Student(String username, char[] pwd, String name, Nationality nationality, Gender gender,
                   String matricNumber, int studyYear, Program program,String email ){

        super(username, pwd, name,nationality, gender,email);
        this.matricNumber = matricNumber;
        this.studyYear = studyYear;
        this.program = program;

    }
    //Constructor for students to be added, without matric number
    /**
     * Constructor for students to be added without matriculation number AND email, used when creating student object from StaffManager
     * @param username Username of student
     * @param pwd Password of student
     * @param name Name of student
     * @param nationality Nationality of student
     * @param gender Gender of student
     * @param studyYear Study year of student
     * @param program Program of student
     */
    public Student(String username, char[] pwd, String name, Nationality nationality, Gender gender,
                   int studyYear, Program program ){

        super(username, pwd, name,nationality, gender);
        this.studyYear = studyYear;
        this.program = program;
    }

    /**
     * Create a copy of the current student
     * @return the student object
     */
    public Student copyObject(){
        return this;
    }

    /**
     * Retrieves matriculation number of student
     * @return matriculation number of student
     */
    public String getMatricNumber() {
        return matricNumber;
    }

    /**
     * Modifies matriculation number of student
     * @param matricNumber matriculation number of student
     */
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    /**
     * Retrieves study year of student
     * @return study year of student
     */
    public int getStudyYear() {
        return studyYear;
    }

    /**
     * Modifies study year of student
     * @param studyYear New study year for student to be set in the student object
     */
    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * Retrieves school of the student
     * @return school of the student
     */
    public School getSchool() {
        return school;
    }

    /**
     * Modifies the school the student belongs to
     * @param school New school for student to be set in the student object
     */
    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * Retrieves the program the student is in
     * @return program of the student
     */
    public Program getProgram() {
        return program;
    }

    /**
     * Modifies the program the student is in
     * @param program New program for student to be set in the student object
     */
    public void setProgram(Program program) {
        this.program = program;
    }
    /**
     * Retrieves the maximum academic units in the semester for the student
     * @return maximum academic unit in the semester for the student
     */
    public int getMaxAUSem() {
        return maxAUSem;
    }

    /**
     * Modifies the maximum academic units in the semester for the student
     * @param maxAUSem New maximum AU for student to be set in the student object
     */
    public void setMaxAUSem(int maxAUSem) {
        this.maxAUSem = maxAUSem;
    }
}
