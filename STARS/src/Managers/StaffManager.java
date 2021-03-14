package Managers;
import Enums.*;
import UserInterface.*;
import Entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * StaffManager contains the logic to coordinate and realise use case
 */
public class StaffManager {
    private Staff staff;
    /**
     * Creates StaffManager with staff attribute
     * @param staff The Staff object that is currently logged in
     */
    public StaffManager(Staff staff){
        this.staff = getStaffFromLogin(staff.getUsername());
    }
    /**
     * Default constructor for staffManager
     */
    public StaffManager(){}

    /**
     * Retrieves list of all staff
     * @return list of staff from data
     */
    public ArrayList<Staff> getListOfAllStaff(){
        FileManager fm = new FileManager();
        ArrayList<Staff> listOfStaff= fm.readStaffData();
        return listOfStaff;
    }

    /**
     * Main method to retrieving user option to execute respective methods
     * @see StaffUI
     */
    public void run(){
        StaffUI staffUI = new StaffUI();
        int choice;
        staffUI = new StaffUI(staff);
        do {
            choice = staffUI.mainPage();
            switch (choice) {
                case 1:
                    editStudentAccessPeriod();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    addNewCourse();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    checkAvailableSlots();
                    break;
                case 6:
                    printStudentListByIndex();
                    break;
                case 7:
                    printStudentListByCourse();
                    break;
                case 8:
                    printAllCourses();
                    break;
                case 9: 
                    FileManager fileManager = new FileManager();
                    fileManager.preload();
                    LoginManager loginManager = new LoginManager();
                    loginManager.startLogin();
                case 0:
                    LoginManager lm = new LoginManager();
                    lm.startLogin();
                    break;
                default:
                    break;
            }
        }while(choice!='#');

    }

    /**
     * Changes the student access period
     * Display current access period, and calls file manage to update data in database
     * @see SemRegistPeriod
     * @see FileManager
     */
    public void editStudentAccessPeriod(){
        StaffUI staffUI = new StaffUI();
        SemRegistManager semManager = new SemRegistManager();
        SemRegistPeriod semRegPeriod = semManager.getCurrentAccessPeriod();

        //Display current access period to staff
        LocalDateTime startPeriod = semRegPeriod.getStartDateTime();
        LocalDateTime endPeriod = semRegPeriod.getEndDateTime();
        staffUI.displayAccessPeriod(startPeriod, endPeriod);

        //Get user confirmation to change access period
        String method = "Edit student access period";
        boolean isChange = staffUI.getUserConfirmation(method);

        if(isChange){
            ArrayList<LocalDateTime> accessPeriodList = new ArrayList<LocalDateTime>();
            accessPeriodList = staffUI.getDateTimeAccessPeriod();
            if(semManager.checkValidAccessPeriod(accessPeriodList)){
                //Update new data and Save to Database
                semManager.addNewAccessPeriod(accessPeriodList);
            }
        }
    }

    /**
     * Adds new student for staff
     * Get staff to enter student information and adds new student to database
     * @see StaffUI
     * @see StudentManager
     * @see FileManager
     * @see Student
     */
    public void addNewStudent(){
        StudentManager stuManager = new StudentManager();
        StaffUI staffUI = new StaffUI();
        Student studentToBeAdded;

        //Get user confirmation to add student
        String addStudentMsg = "To add student, ensure that you have the following information ready: \n"+
                               "Name, UserID, Password, Nationality, Gender, StudyYear and Program";
        String addStudentOption = "Add student";
        boolean willAdd = staffUI.getUserConfirmationWithMsg(addStudentOption, addStudentMsg);
        if(willAdd){
            studentToBeAdded = staffUI.getStudentInfo();
            if(!stuManager.checkStudentExist(studentToBeAdded.getUsername())){
                //Display student details to be added
                staffUI.displayStudentDetails(studentToBeAdded);

                //Prompt user to confirm decision
                String optionName = "Confirm Add Student";
                boolean confirmAdd = staffUI.getUserConfirmation(optionName);

                if(confirmAdd){
                    //Add student
                    ArrayList<Student> listOfAllStudent = stuManager.getListOfAllStudents();
                    listOfAllStudent.add(studentToBeAdded);
                    //Save to database
                    FileManager fm = new FileManager();
                    fm.writeStudentData(listOfAllStudent);

                    //Display list of all students
                    staffUI.displayStudentList(listOfAllStudent);
                }
            }
        }
    }
    /**
     * Generates a unique matric number for new student
     * @see Random
     * @return matric number
     */
    public String generateMatricNumber(){
        boolean isInUse = false;
        String matricNumber = "";
        String matricStart = "";
        String matricEnd = "";
        String matricMiddle = "";

        do{
            //Decide the starting letter to represent AY
            StudentManager stm = new StudentManager();
            String[] listOfletters = {"N", "U","G" ,"E" ,"T","F","A","V","O","R"};
            int currentYear = LocalDate.now().getYear();
            matricStart = listOfletters[currentYear%listOfletters.length];

            //Random generate rest of 7 digits
            Random r = new Random();
            matricMiddle = r.nextInt((9999999-1000000)+1)+1000000+"";

            //Add random letter at the end
            matricEnd = (char) (r.nextInt(26)+'a')+"";
            matricEnd = matricEnd.toUpperCase();

            //Check if current matric number exists
            matricNumber = matricStart+matricMiddle+matricEnd;
            ArrayList<Student> listOfStudent = stm.getListOfAllStudents();
            for(Student s : listOfStudent){
                if(s.getMatricNumber().equals(matricNumber)){
                    isInUse = true;
                    break;
                }
            }
        }while(isInUse);
        return matricNumber;
    }
    /**
     * Generates a unique email for new student
     * @return email
     */
    public String generateStudentEmail() {
        boolean isInUse = false;
        String email = "";
        do {
            String body = "@e.ntu.edu.sg";
            String head = "";
            Random r = new Random();
            head = head + r.nextInt((9999 - 1000) + 1) + 1000 + "";
            head = (char)(r.nextInt(26) + 'a') + "" + head;

            email = head + body;
            StudentManager sm = new StudentManager();
            ArrayList<Student> listOfStudent = sm.getListOfAllStudents();

            for (Student s : listOfStudent) {
                if (s.getEmail().equals(email)) {
                    isInUse = true;
                    break;
                }
            }
        }while (isInUse) ;
        return email;
    }
    /**
     * Adds new course for staff
     * Asks for staff to enter course details and calls filemanager to save it to database
     * @see StaffUI
     * @see FileManager
     * @see CourseManager
     */
    public void addNewCourse(){
        StaffUI staffUI = new StaffUI();
        FileManager fm = new FileManager();
        CourseManager cm = new CourseManager();
        //Display current courses
        ArrayList<Course> listOfAllCourses = cm.getAllCourses();
        staffUI.displayListOfCourse(listOfAllCourses);

        String methodName = "Add Course";
        boolean willAddCourse = staffUI.getUserConfirmation(methodName);

        if(willAddCourse){
            //Prompt user to add course
            Course courseToBeAdded;
            courseToBeAdded = staffUI.getCourseInfo();

            if(!cm.checkCourseExist(courseToBeAdded.getCourseCode())){
                //Save to database
                listOfAllCourses.add(courseToBeAdded);
                fm.writeCourseData(listOfAllCourses);

                //Display list of all courses
                staffUI.displayListOfCourse(listOfAllCourses);
            }
            else{
                ErrorMessage em = new ErrorMessage();
                staffUI.printErrorMessage(em.COURSE_ALREADY_EXISTS);
            }
        }
    }

    /**
     * Updates course or index of course
     * Asks for staff input to enter the respective details for course or index and calls filemanager to save it to database
     */
    public void updateCourse(){
        //Display all courses
        CourseManager cm = new CourseManager();
        StaffUI staffUI = new StaffUI();
        FileManager fm = new FileManager();
        ArrayList<Course> listOfAllCourses = cm.getAllCourses();
        staffUI.displayListOfCourse(listOfAllCourses);

        //Prompt user to enter course code
        String courseCode;
        courseCode = staffUI.getStrInputWithinRange("Enter the course code (6 characters e.g -> CZ1011): ",6,6);
            
        if(cm.checkCourseExist(courseCode)){
            Course selectedCourse = cm.getCourseWithCourseCode(courseCode);
            //Display new current course, ask for confirmation
            ArrayList<Course> courseList = new ArrayList<Course>();
            courseList.add(selectedCourse);
            staffUI.displayListOfCourse(courseList);
            String optionName = "Update Course/Index or Add Index ";
            boolean isUpdate = staffUI.getUserConfirmation(optionName);

            if(isUpdate){
                String[] updateOptions = {"Change course code","Change course School", "Change course Index","Add course Index"};
                int choice = staffUI.getUserMultipleOptions(updateOptions);
                switch(choice){
                    case 1:// CASE 1: Change course code
                        //Prompt user to enter new course code
                        String newCourseCode = "";
                        do{
                            newCourseCode = staffUI.getStrInputWithinRange("Enter new course code (6 characters): ",6,6);
                            //check if courseCode exist
                            ErrorMessage em = new ErrorMessage();
                            if(cm.checkCourseExist(newCourseCode)){staffUI.printErrorMessage(em.COURSE_ALREADY_EXISTS);}
                        }while(cm.checkCourseExist(newCourseCode));
                        selectedCourse.setCourseCode(newCourseCode);
                        break;
                    case 2://CASE 2:Change course school
                        //Prompt user to select new School
                        School newSchool = staffUI.getSchool();
                        selectedCourse.setSchool(newSchool);
                        break;
                    case 3://CASE 3: Change course Index
                        //Display course indexes
                        String[] courseCodeList = {courseCode};
                        ArrayList<Index> courseIndexList = cm.getIndexesFromCourse(courseCodeList);
                        staffUI.displayIndexes(courseIndexList);

                        //Prompt user to select which index
                        String changeIndexMessage ="Please select the index (1,2, etc) to change: ";
                        int indexChoice = staffUI.getIntInputWithinRange(changeIndexMessage, 1,courseIndexList.size());
                        Index selectedIndex = courseIndexList.get(indexChoice-1);

                        //Show current options to edit course: change index number and change vacancy
                        String[] editOptions = {"Change max vacancy of Index","Change Index group name"};
                        int editChoice = staffUI.getUserMultipleOptions(editOptions);
                        switch(editChoice){
                            case 1://Change vacancy of Index
                                //Prompt user to enter new vacancy number
                                String newVacancyMsg = "Enter the new max vacancy for index (max 2000 slots): ";
                                int newVacancy = staffUI.getIntInputWithinRange(newVacancyMsg,1,2000);
                                selectedIndex.setMaxVacancy(newVacancy);

                                break;
                            case 2: //Change group name
                                //Prompt user to enter new group name
                                String enterNewGrpMsg = "Enter the new index group name (max 5 characters).";
                                String newGroup = staffUI.getStrInputWithinRange(enterNewGrpMsg,1,5);
                                selectedIndex.setGroupName(newGroup);
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////
                        System.out.println(selectedIndex);
                        //Replace the index with the updated one into course
                        selectedCourse = cm.updateIndex(selectedCourse, selectedIndex);
                        break;
                    case 4: //CASE 4: Add course Index
                        //Prompt user to add new index
                        Index newIndex = new Index();
                        //check if course id exist
                        do{
                            newIndex = staffUI.getIndexInfo();
                        }while(cm.checkIndexIDExist(newIndex.getIndexID(), selectedCourse.getCourseCode()));
                        selectedCourse.getIndexes().add(newIndex);
                        break;
                }
                //Update list of courses with updated index
                for(Course c : listOfAllCourses){
                    if(c.getCourseName().equals(selectedCourse.getCourseName())
                    || c.getCourseCode().equals(selectedCourse.getCourseCode())){
                        int index = listOfAllCourses.indexOf(c);
                        listOfAllCourses.set(index, selectedCourse);
                        break;
                    }
                }

                //display newly updated course list
                staffUI.displayListOfCourse(listOfAllCourses);
                //Save to database
                fm.writeCourseData(listOfAllCourses);
            }
        }
        else{
            ErrorMessage em =  new ErrorMessage();
            staffUI.printErrorMessage(em.COURSE_DOES_NOT_EXIST);
        }
    }

    /**
     * Check available slot for specified Index and display the information
     * @see StaffUI
     * @see CourseManager
     */
    public void checkAvailableSlots(){
        StaffUI staffUI = new StaffUI();
        CourseManager cm = new CourseManager();
        //Prompt user to enter index number
        String indexQuestion = "Please enter Index ID (e.g 11023):";
        String indexID = "";

        indexID = staffUI.getStrInputWithinRange(indexQuestion,5,5);
        if(cm.checkIfIndexExist(indexID)){
            ArrayList<Index> listOfIndex = new ArrayList<Index>();
            Index selectedIndex = cm.getIndexFromID(indexID);
            listOfIndex.add(selectedIndex);
            //Display index details and vacancy
            staffUI.displayIndexes(listOfIndex);
        }
    }

    /**
     * Display student list in Index based of index ID provided
     * @see StaffUI
     * @see CourseManager
     * @see StudentManager
     */
    public void printStudentListByIndex(){
        StudentManager sm = new StudentManager();
        StaffUI staffUI = new StaffUI();
        CourseManager cm = new CourseManager();
        //Prompt user to enter index number
        String studentQuestion = "Please enter Index ID (e.g 11023):";
        String indexID = "";
        indexID = staffUI.getStrInputWithinRange(studentQuestion,5,5);
        if(cm.checkIfIndexExist(indexID)){
            Index selectedIndex = cm.getIndexFromID(indexID);

            ArrayList<Student> listOfStudent = new ArrayList<Student>();
            for(String matricNumber : selectedIndex.getStudentsRegistered()){
                Student student = sm.getStudentFromMatric(matricNumber);
                listOfStudent.add(student);
            }

            //Display student list by index
            staffUI.displayStudentList(listOfStudent);
        }
    }
    /**
     * Display student list in Course based of course ID provided
     * @see StaffUI
     * @see CourseManager
     * @see StudentManager
     */
    public void printStudentListByCourse(){
        StudentManager sm = new StudentManager();
        CourseManager cm = new CourseManager();
        StaffUI staffUI = new StaffUI();
        //Prompt user to enter index number
        String stuCourseQuestion = "Please enter Course Code (6 characters, e.g -> CZ1011):";
        String courseCode = "";
        //Prompt user to enter course code
        courseCode = staffUI.getStrInputWithinRange(stuCourseQuestion,6,6);

        if(cm.checkCourseExist(courseCode)){
            //Display all students
            Course selectedCourse = cm.getCourseWithCourseCode(courseCode);

            //Initialize lists to store registered students
            ArrayList<Student> registeredStudents = new ArrayList<Student>();

            for(Index ind : selectedCourse.getIndexes()){
                for(String matricNumber : ind.getStudentsRegistered()){
                    Student student = sm.getStudentFromMatric(matricNumber);
                    registeredStudents.add(student);
                }
            }
            staffUI.displayStudentByCourse(registeredStudents,courseCode);
        }


    }
    /**
     * Valid staff login credentials
     * @see LoginManager
     * @param userInfo The login credentials of staff to be validated
     * @return true if login credentials match in database, false if login credentials does not match in database
     */
    public boolean validateStaff(User userInfo){
        ArrayList<Staff> staffList = getListOfAllStaff();
        for(Staff staff : staffList){
            //User is found
            if(staff.getUsername().equals(userInfo.getUsername())){
                if(Arrays.equals(staff.getPassword(), userInfo.getPassword())){
                    return true;
                }
            }
        }
        ErrorMessage em = new ErrorMessage();
        StaffUI staffUI = new StaffUI();
        staffUI.printErrorMessage(em.INVALID_USERNAME_PASSWORD_COMBINATION);
        return false;
    }
    /**
     * Retrieves the staff object based on username
     * @param username Username of staff
     * @return Staff object
     */
    public Staff getStaffFromLogin(String username){
        FileManager fm = new FileManager();
        ArrayList<Staff> staffList = fm.readStaffData();

        for(Staff staff : staffList){
            if(staff.getUsername().equals(username)){
                return staff;
            }
        }
        Staff emptyStaff = new Staff();
        return emptyStaff;
    }
    /**
     * Displays all courses currently in data
     * @see StaffUI
     * @see CourseManager
     */
    public void printAllCourses(){
        StaffUI staffUI = new StaffUI();
        CourseManager cm = new CourseManager();
        ArrayList<Course> listOfCourse = cm.getAllCourses();
        staffUI.displayListOfCourse(listOfCourse);
    }
}
