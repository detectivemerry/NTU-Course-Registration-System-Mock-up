package Managers;

import UserInterface.*;
import Entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * StudentManager contains the logic to coordinate and realise use case for
 * students
 */
public class StudentManager {
    Student student;

    /**
     * Creates StudentManager with Student attribute
     * 
     * @param student Student object that is logged in
     */
    public StudentManager(Student student) {
        this.student = getStudentFromLogin(student.getUsername());
    }

    /**
     * Default constructor for student manager
     */
    public StudentManager() {
    }

    /**
     * Main method to retrieving user option to execute respective methods
     * 
     * @see StaffUI
     */
    public void run() {
        StudentUI studentUI = new StudentUI();
        int choice;
        studentUI = new StudentUI(student);
        do {
            choice = studentUI.mainPage();
            switch (choice) {
            case 1:
                handleAddCourse();
                break;
            case 2:
                handleDropCourse();
                break;
            case 3:
                handlePrintCourse();
                break;
            case 4:
                checkIndexVacancy();
                break;
            case 5:
                handleChangeIndexNoOfCourse();
                break;
            case 6:
                handleSwopIndexWithStudent();
                break;
            case 0:
                LoginManager lm = new LoginManager();
                lm.startLogin();
                break;
            default:
                break;
            }
        } while (choice != '#');
    }

    /**
     * Handles the add course process, prompts user to add course details and check
     * if course is valid to be added
     * 
     * @see CourseManager
     * @see StudentUI
     * @see StaffManager
     */
    public void handleAddCourse() {
        CourseManager cm = new CourseManager();
        StudentUI studentUI = new StudentUI();
        String indexIDToBeAdded;
        StaffManager sm = new StaffManager();
        sm.printAllCourses();

        // Ask user to enter index number
        String addCourseMsg = "Enter the index number to be added (Max 5 characters): ";
        indexIDToBeAdded = studentUI.getStrInputWithinRange(addCourseMsg, 5, 5);

        // Check if index number can be added
        if (cm.checkIfIndexExist(indexIDToBeAdded)
                && !checkIfStudentHasCourse(indexIDToBeAdded, student.getMatricNumber())
                && !cm.checkIfIndexClash(indexIDToBeAdded, student.getMatricNumber())) {
            // Display index details
            HashMap<Course, Index> indexInfo = new HashMap<Course, Index>();
            Course courseToBeAdded = cm.getCourseWithIndex(indexIDToBeAdded);
            Index indexToBeAdded = cm.getIndexFromID(indexIDToBeAdded);
            indexInfo.put(courseToBeAdded, indexToBeAdded);
            studentUI.displayIndexWithCourse(indexInfo);

            // Ask for user confirmation to add index
            String methodName = "Add course";
            boolean willAddCourse = studentUI.getUserConfirmation(methodName);

            if (willAddCourse) {
                if (cm.indexIsVacant(indexIDToBeAdded)) {
                    // If index is vacant, add student to course
                    cm.addStudentToCourse(student.getMatricNumber(), indexIDToBeAdded);
                    // print all course taken by student
                    HashMap<Course, Index> studentCourses = cm.getRegisteredCourseAndIndex(student.getMatricNumber());
                    ///////// BUG////////////
                    studentUI.printCourseDisplay(studentCourses, student.getMatricNumber());
                } else {
                    // If index is not vacant, add student to waiting list of course
                    cm.addStudentToWaitingList(student.getMatricNumber(), indexIDToBeAdded);
                    // Notify student about being added to waiting list

                    int position = cm.getWaitListPosition(student.getMatricNumber(), indexIDToBeAdded);
                    GeneralMessage gm = new GeneralMessage();
                    studentUI.printGeneralMessage(gm.ADDED_TO_WAIT_LIST + " Position in wait list = " + position);

                }
            }
        }
    }

    /**
     * Handles the drop course process, prompts user to enter drop course details
     * and drop course If any, a student in waitlist will be registered into course
     * in method, dropStudentFromCourse
     * 
     * @see StudentUI
     * @see CourseManager
     */
    public void handleDropCourse() {
        StudentUI studentUI = new StudentUI();
        CourseManager courseManager = new CourseManager();

        // Get information based on course list taken by student
        HashMap<Course, Index> coursesAndIndexTakenByStudent = courseManager
                .getRegisteredCourseAndIndex(student.getMatricNumber());

        // Call UI to get user input on which index to drop
        int index;
        studentUI.displayIndexNameWithCourse(coursesAndIndexTakenByStudent);
        String indexQst = "Select the course to drop (e.g 1,2, etc): ";
        index = studentUI.getIntInputWithinRange(indexQst, 1, coursesAndIndexTakenByStudent.size());

        // Get selected course and index combination
        int courseCounter = 1;

        // Find the course selected by student and drop course
        for (Course c : coursesAndIndexTakenByStudent.keySet()) {
            if (courseCounter == index) {
                Index indexToBeDropped = coursesAndIndexTakenByStudent.get(c);
                HashMap<Course, Index> courseToBeDeleted = new HashMap<Course, Index>();
                courseToBeDeleted.put(c, indexToBeDropped);
                studentUI.displayIndexWithCourse(courseToBeDeleted);
                String deleteMsg = "Confirm that you want to drop this course";
                boolean willDelete = studentUI.getUserConfirmation(deleteMsg);

                if (willDelete) {
                    // Drop student from course, add next student on waitingList to registered
                    courseManager.dropStudentFromCourse(indexToBeDropped, student.getMatricNumber());
                }
                break;
            }
            courseCounter++;
        }
    }

    /**
     * Check if student has index specified using the index ID
     * 
     * @param index        Index ID of index
     * @param matricNumber matric number of student
     * @return true if student has index and false if student does not have index
     */
    public boolean checkIfStudentHasIndex(String index, String matricNumber) {
        ErrorMessage em = new ErrorMessage();
        CourseManager cm = new CourseManager();
        StudentUI studentUI = new StudentUI();
        HashMap<Course, Index> userRegisteredCourse = cm.getRegisteredCourseAndIndex(matricNumber);
        for (Course c : userRegisteredCourse.keySet()) {
            if (userRegisteredCourse.get(c).getIndexID().equals(index)) {
                return true;
            }

        }
        studentUI.printErrorMessage(em.USER_DOES_NOT_HAVE_INDEX);
        return false;
    }

    /**
     * Check if student has course, from the index specified using the index ID
     * 
     * @param index        index ID of index
     * @param matricNumber matric number of student
     * @return true if student has course, false if student does not have course
     */
    public boolean checkIfStudentHasCourse(String index, String matricNumber) {
        // System.out.println("Check if student has course is running");
        ErrorMessage em = new ErrorMessage();
        CourseManager cm = new CourseManager();
        StudentUI studentUI = new StudentUI();
        HashMap<Course, Index> userRegisteredCourse = cm.getRegisteredOrWaitListCourseAndIndex(matricNumber);

        // Find Index course
        Course selectedCourse = cm.getCourseWithIndex(index);

        // Check if student is already registered in selected course
        for (Course c : userRegisteredCourse.keySet()) {
            if (c.getCourseCode().equals(selectedCourse.getCourseCode())) {
                studentUI.printErrorMessage(em.USER_ALREADY_REGISTERED_IN_COURSE);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all courses that student is registered in
     * 
     * @see StudentUI
     * @see CourseManager
     */
    public void handlePrintCourse() {
        CourseManager cm = new CourseManager();
        StudentUI studentUI = new StudentUI();
        // Get information based on course list taken by student
        HashMap<Course, Index> coursesAndIndexTakenByStudent = cm
                .getRegisteredOrWaitListOrExemptedCourseAndIndex(student.getMatricNumber());
        studentUI.printCourseDisplay(coursesAndIndexTakenByStudent, student.getMatricNumber());
    }

    /**
     * Prints index vacancy details according to Index ID entered by user
     * 
     * @see CourseManager
     * @see StudentUI
     */
    public void checkIndexVacancy() {
        CourseManager cm = new CourseManager();
        StudentUI studentUI = new StudentUI();
        String indexNumber;
        // Prompt user to enter an index number that exists
        String getIndexMsg = "Enter the index number (5 characters): ";
        indexNumber = studentUI.getStrInputWithinRange(getIndexMsg, 5, 5);
        if (cm.checkIfIndexExist(indexNumber)) {
            // Display selected index
            Course indexCourse = cm.getCourseWithIndex(indexNumber);
            Index index = cm.getIndexFromID(indexNumber);
            HashMap<Course, Index> courseInfo = new HashMap<Course, Index>();
            courseInfo.put(indexCourse, index);
            studentUI.displayIndexWithCourse(courseInfo);
        }
    }

    /**
     * Handles the change of index of course Validates if the two indexes is valid
     * to switch, if valid, swop the two indexes
     * 
     * @see CourseManager
     * @see StudentUI
     */
    public void handleChangeIndexNoOfCourse() {
        StudentUI studentUI = new StudentUI();
        CourseManager cm = new CourseManager();
        String[] indexArr;
        String currentIndexID;
        String newIndexID;
        // Get 2 indexes from student, current index and new index
        indexArr = studentUI.changeIndexNoOfCourseGetIndex();
        currentIndexID = indexArr[0];
        newIndexID = indexArr[1];

        // If student already has index, or index is from different courses, or index is
        // vacant,
        // or index will clash with existing time table, Prompt user to enter index
        // again
        if (checkIfStudentHasIndex(currentIndexID, student.getMatricNumber())
                && cm.isIndexFromSameCourse(currentIndexID, newIndexID) && cm.indexIsVacant(newIndexID)
                && !cm.checkIfTwoIndexClash(newIndexID, currentIndexID, student.getMatricNumber())) {
            // Display index information
            Course course = cm.getCourseWithIndex(currentIndexID);
            Index currentIndex = cm.getIndexFromID(currentIndexID);
            Index newIndex = cm.getIndexFromID(newIndexID);
            HashMap<Course, Index> indexCourseInfo = new HashMap<Course, Index>();
            indexCourseInfo.put(course, currentIndex);
            indexCourseInfo.put(course, newIndex);
            studentUI.displayChangeIndex(indexCourseInfo);

            // Switch index
            String changeOption = "Change index";
            boolean willChange = studentUI.getUserConfirmation(changeOption);
            if (willChange) {
                // Switch index
                cm.switchIndex(currentIndexID, newIndexID, student.getMatricNumber());
            }
        }
    }

    /**
     * Handles the swop index between students Validates the peer login and if the
     * two indexes are both valid to switch and if valid, swop student indexes
     * 
     * @see LoginManager
     * @see CourseManager
     * @see StudentUI
     */
    public void handleSwopIndexWithStudent() {
        StudentUI studentUI = new StudentUI();
        LoginManager lm = new LoginManager();
        CourseManager cm = new CourseManager();
        SwopIndexInfo si;
        String[] indexArr = new String[2];
        User peer;
        String currentStudentMatric, peerMatric;

        // Get swop information from student
        si = studentUI.swopIndexWithStudentGetInfo();
        indexArr[0] = si.getCurrentStudentIndex();
        indexArr[1] = si.getPeerIndex();

        peer = new User(si.getPeerUsername(), si.getPeerPassword());
        currentStudentMatric = student.getMatricNumber();
        peerMatric = getMatricFromUsername(si.getPeerUsername());

        // Check if the two indexes are valid to switch AND check if peer login is a
        // success
        // Valid to switch iff both student has their indexes and are from same course
        // and will not clash with each other current time table
        if (lm.validateStudent(peer) && checkIfStudentHasIndex(si.getCurrentStudentIndex(), currentStudentMatric)
                && checkIfStudentHasIndex(si.getPeerIndex(), peerMatric)
                && cm.isIndexFromSameCourse(si.getCurrentStudentIndex(), si.getPeerIndex())
                && !cm.checkIfTwoIndexClash(si.getPeerIndex(), si.getCurrentStudentIndex(), currentStudentMatric)
                && !cm.checkIfTwoIndexClash(si.getCurrentStudentIndex(), si.getPeerIndex(), peerMatric)) {

            // Display user index and peer index
            LinkedHashMap<Course, Index> indexCourseInfo = new LinkedHashMap<Course, Index>();
            // Get student index details to display
            Index currentStudentIndex = cm.getIndexFromID(si.getCurrentStudentIndex());
            Course currentStudentCourse = cm.getCourseWithIndex(si.getCurrentStudentIndex());
            indexCourseInfo.put(currentStudentCourse, currentStudentIndex);
            // Get peer index to display
            Index peerIndex = cm.getIndexFromID(si.getPeerIndex());
            Course peerCourse = cm.getCourseWithIndex(si.getPeerIndex());
            indexCourseInfo.put(peerCourse, peerIndex);
            String studentMatric[] = { currentStudentMatric, peerMatric };
            studentUI.displaySwopStudentCourseInfo(studentMatric, indexCourseInfo);

            // Prompt user to confirm swop
            String swopOption = "Swop indexes";
            boolean confirmSwop = studentUI.getUserConfirmation(swopOption);

            // Swop indexes
            if (confirmSwop) {
                cm.switchIndex(si.getCurrentStudentIndex(), si.getPeerIndex(), currentStudentMatric);
                cm.switchIndex(si.getPeerIndex(), si.getCurrentStudentIndex(), peerMatric);

                // Notify users of swop success with email
                if (cm.notifySwopSuccess(currentStudentMatric, peerMatric, currentStudentCourse, peerCourse,
                        si.getCurrentStudentIndex(), si.getPeerIndex())) {
                    GeneralMessage gm = new GeneralMessage();
                    studentUI.printGeneralMessage(gm.NOTIFICATION_SENT);
                } else {
                    ErrorMessage em = new ErrorMessage();
                    studentUI.printGeneralMessage(em.EMAIL_UNABLE_TO_SEND);
                }

                String finishSwopMsg = currentStudentMatric + " - Index Number" + si.getCurrentStudentIndex()
                        + " has been successfully swopped with" + peerMatric + " - Index Number" + si.getPeerIndex();
                String finishSwopOption = "OK";
                studentUI.getUserConfirmationWithMsg(finishSwopOption, finishSwopMsg);
            }
        }
    }

    /**
     * Retrieves the student matric number from the student username
     * 
     * @param userName username of student
     * @see Student
     * @return matric number of student
     */
    public String getMatricFromUsername(String userName) {
        // Get all students
        ArrayList<Student> listOfStudents = getListOfAllStudents();
        for (Student s : listOfStudents) {
            if (s.getUsername().equals(userName)) {
                return s.getMatricNumber();
            }
        }

        return "";
    }

    /**
     * Retrieves the Student object from student matric number
     * 
     * @param matricNumber matric number of student
     * @return Student object
     */
    public Student getStudentFromMatric(String matricNumber) {
        Student nullStudent = new Student();
        ArrayList<Student> listOfStudent = getListOfAllStudents();
        for (Student s : listOfStudent) {
            if (matricNumber.equals(s.getMatricNumber())) {
                return s;
            }
        }
        return nullStudent;
    }

    /**
     * Retrieves the list of students from data
     * 
     * @see FileManager
     * @return list of students
     */
    public ArrayList<Student> getListOfAllStudents() {
        FileManager fm = new FileManager();
        return fm.readStudentData();
    }

    /**
     * Checks if student exists in data
     * 
     * @param userName username of student
     * @see Student
     * @return true if student can be found in database, false if student cannot be
     *         found in database
     */
    public boolean checkStudentExist(String userName) {
        StudentUI studentUI = new StudentUI();
        ArrayList<Student> listOfStudents = getListOfAllStudents();
        for (Student s : listOfStudents) {
            if (s.getUsername().equals(userName)) {
                ErrorMessage em = new ErrorMessage();
                studentUI.printErrorMessage(em.USER_ALREADY_EXISTS);
                return true;
            }
        }
        return false;
    }

    /**
     * Retreives student object from login
     * 
     * @param username username of student
     * @return Student object
     */
    public Student getStudentFromLogin(String username) {
        ArrayList<Student> studentList = getListOfAllStudents();

        for (Student student : studentList) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        Student emptyStudent = new Student();
        return emptyStudent;
    }

    /**
     * Valid student login credentials
     * 
     * @param userInfo The login credentials of staff to be validated
     * @see LoginManager
     * @return true if login credentials match in database, false if login
     *         credentials does not match in database
     */
    public boolean validateStudent(User userInfo) {
        StudentUI studentUI = new StudentUI();
        ArrayList<Student> studentList = getListOfAllStudents();
        for (Student student : studentList) {
            // User is found
            if (student.getUsername().equals(userInfo.getUsername())) {
                if (Arrays.equals(student.getPassword(), userInfo.getPassword())) {
                    return true;
                }
            }
        }
        ErrorMessage em = new ErrorMessage();
        studentUI.printErrorMessage(em.INVALID_USERNAME_PASSWORD_COMBINATION);
        return false;
    }

}
