package UserInterface;
import Entities.*;
import Enums.*;
import Managers.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * StaffUI handles the Interaction between System and Staff
 */
public class StaffUI extends MainUI{
    Staff staff;
    /**
     * Creates a StaffUI object with staff attribute
     * @param staff object here will provide the details for the page header
     */
    public StaffUI(Staff staff){
        this.staff = staff;
    }
    /**
     * Default constructor for staff UI
     */
    public StaffUI(){}
    /**
     * Displays the main header and Retrieves student input to run respective methods
     */
    @Override
    public int mainPage() {
        getPageHeader();
        System.out.println("Redirecting to main page...");
        //display all options for staff
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select one of the functions:");
        String[] options = getUserOptions();
        for(int i = 0; i < options.length; i++){
            System.out.println(options[i]);
        }

        return sc.nextInt();
    }
     /**
     * Retrieves a list of options available for Student
     */
    @Override
    public String[] getUserOptions(){
        String[] options = new String[]{
                "1. Edit Student Access Period",
                "2. Add new Student",
                "3. Add new Course",
                "4. Update Course/Index or Add Index ",
                "5. Check Available Slots for an index Number",
                "6. Print student list by index Number",
                "7. Print student list by course",
                "8. Print all courses",
                "9. Reset database to original data",
                "0. Exit program"
        };
        return options;
    }
      /**
     * Displays header for Student
     */
    @Override
    public void getPageHeader() {
        String studentName = "Name: "+ staff.getName();
        System.out.println("|-----------------------------------------------------------------------------------------------------------|\n" +
                "|                                                                                                           |\n" +
                "|"+addSpace("STARS")+"|\n"+
                "|"+addSpace("")+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|\n"+
                "|"+addSpace(studentName)+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|");

    }
    /**
     * Displays the day, month and year values of start and end period
     * @param startPeriod Start of access period
     * @param endPeriod End of access period
     * @see LocalDateTime
     */
    public void displayAccessPeriod(LocalDateTime startPeriod, LocalDateTime endPeriod){
        int startDay = startPeriod.getDayOfMonth();
        int startMonth = startPeriod.getMonthValue();
        int startYear = startPeriod.getYear();
        int startHour = startPeriod.getHour();
        int startMinute = startPeriod.getMinute();
        int endDay = endPeriod.getDayOfMonth();
        int endMonth =endPeriod.getMonthValue();
        int endYear =endPeriod.getYear();
        int endHour = endPeriod.getHour();
        int endMinute = endPeriod.getMinute();

        printPageBreak();
        System.out.println("Current Student access Period:");
        System.out.println("Start date: " + startDay + "/" + startMonth + "/" + startYear);
        System.out.println("Start time: " + startHour + ":" + startMinute);
        System.out.println("");
        System.out.println("End date: " + endDay + "/" + endMonth + "/" + endYear);
        System.out.println("End time: " + endHour + ":" + endMinute);
        System.out.println("");
    }
    /**
     * Interface for staff to enter new access period details, day, month, year for both start and end of access period
     * @return List of LocalDateTime objects, containing start and end period 
     */
    public ArrayList<LocalDateTime> getDateTimeAccessPeriod(){
        ArrayList<LocalDateTime> newAccessPeriodList = new ArrayList<LocalDateTime>();
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        try {
            System.out.println("Enter the new Access period START date and time (DD/MM/YYYY and HH:MM required): ");
            String startPeriodDayQst = "Enter start period DAY (DD)";
            int startPeriodDay = getIntInputWithinRange(startPeriodDayQst, 1, 31);
            String startPeriodMonthQst = "Enter start period MONTH (MM)";
            int startPeriodMonth = getIntInputWithinRange(startPeriodMonthQst, 1, 30);
            String startPeriodYearQst = "Enter start period YEAR (YYYY)";
            int startPeriodYear = getIntInputWithinRange(startPeriodYearQst, 1950, 2500);
            String startPeriodHourQst = "Enter start period HOUR (HH in 24H format)";
            int startPeriodHour = getIntInputWithinRange(startPeriodHourQst, 0, 23);
            String startPeriodMinQst = "Enter start period MINUTE (MM)";
            int startPeriodMin = getIntInputWithinRange(startPeriodMinQst, 0, 59);

            startDateTime = LocalDateTime.of(startPeriodYear, startPeriodMonth, startPeriodDay,
                    startPeriodHour,startPeriodMin,0,0);

            System.out.println("Enter the new Access period END date and time (DD/MM/YYYY and HH:MM required): ");
            String endPeriodDayQst = "Enter end period DAY (DD)";
            int endPeriodDay = getIntInputWithinRange(startPeriodDayQst, 1, 31);
            String endPeriodMonthQst = "Enter end period MONTH (MM)";
            int endPeriodMonth = getIntInputWithinRange(startPeriodMonthQst, 1, 30);
            String endPeriodYearQst = "Enter end period YEAR (YYYY)";
            int endPeriodYear = getIntInputWithinRange(startPeriodYearQst, 1950, 2500);
            String endPeriodHourQst = "Enter end period HOUR (HH in 24H format)";
            int endPeriodHour = getIntInputWithinRange(startPeriodHourQst, 0, 23);
            String endPeriodMinQst = "Enter end period MINUTE (MM)";
            int endPeriodMin = getIntInputWithinRange(startPeriodMinQst, 0, 59);

            endDateTime = LocalDateTime.of(endPeriodYear, endPeriodMonth, endPeriodDay,
                    endPeriodHour,endPeriodMin,0,0);

            newAccessPeriodList.add(startDateTime);
            newAccessPeriodList.add(endDateTime);
        }
        catch (NumberFormatException e){
            printErrorMessage(em.USER_INPUT_INVALID_TYPE);
        }
        catch (DateTimeException e){
            printErrorMessage(em.INVALID_DATE_OR_TIME);
        }
        return newAccessPeriodList;
    }
    /**
     * Interface for staff to key in details for new student
     * @return Student object created from the details
     */
    public Student getStudentInfo(){
        Scanner sc = new Scanner(System.in);
        printPageBreak();
        System.out.println("Key in the details for the new student: ");
        String studentNameQst = "Enter the name of the student (Enter 2 to 50 characters): ";
        String studentName = getStrInputWithinRange(studentNameQst, 2, 50);
        String studentUserIDQst = "Enter the userID for the student (Enter 7 characters -> e.g C190106): ";
        String userID = getStrInputWithinRange(studentUserIDQst, 7, 7);
        String userPassQst = "Enter the default password for the student (Enter 4 to 18 characters): ";
        String userPassword = getStrInputWithinRange(userPassQst, 4, 18);
        char userPasswordValue[] = new char[userPassword.length()];

        for(int i = 0; i < userPassword.length(); i++){
            userPasswordValue[i] = userPassword.charAt(i);
        }

        String studyYearQst = "Enter the study year of student (Enter 1 to 10 -> e.g year 1, 2,...): ";
        int studyYear = getIntInputWithinRange(studyYearQst,1,10);
        
        String emailMsg = "Enter your email (Between 6 to 40 chararacters)";
        String userEmail = getStrInputWithinRange(emailMsg, 6, 40);

        System.out.println("List of nationalities: ");
        ArrayList<Nationality> listOfNationality = new ArrayList<Nationality>(Arrays.asList(Nationality.values()));
        for(int i = 0; i < listOfNationality.size(); i++){
            System.out.println(i+1+"."+listOfNationality.get(i));
        }
        String nationalityQst = "Select the student nationality: ";
        int nationalityChoice = getIntInputWithinRange(nationalityQst, 1,listOfNationality.size()) - 1;
        Nationality userNationality = listOfNationality.get(nationalityChoice);

        System.out.println("List of Genders: ");
        ArrayList<Gender> listOfGender = new ArrayList<Gender>(Arrays.asList(Gender.values()));
        for(int i = 0; i < listOfGender.size(); i++){
            System.out.println(i+1+". "+listOfGender.get(i));
        }
        String genderQst = "Select the user Gender";
        int genderChoice = getIntInputWithinRange(genderQst, 1,listOfGender.size()) - 1;
        Gender userGender = listOfGender.get(genderChoice);

        System.out.println("List of Programs:");
        ArrayList<Program> listOfProgram = new ArrayList<Program>(Arrays.asList(Program.values()));
        for(int i = 0; i < listOfProgram.size(); i++){
            System.out.println(i+1+". "+listOfProgram.get(i));
        }
        String programQst = "Select the user Program";
        int programChoice = getIntInputWithinRange(programQst, 1,listOfProgram.size()) - 1;
        Program userProgram = listOfProgram.get(programChoice);

        StaffManager staffManager = new StaffManager();
        String userMatricNumber = staffManager.generateMatricNumber();

        Student s = new Student(userID, userPasswordValue, studentName, userNationality, 
        userGender, userMatricNumber, studyYear, userProgram, userEmail);
        return s;
    }
    /**
     * Displays list of course with details, code, name, sem, AU, school, courseType, and course index ID
     * @param listOfCourse List of course to be displayed
     */
    public void displayListOfCourse(ArrayList<Course> listOfCourse){
        printPageBreak();
        System.out.println("Selected course(s): ");
        System.out.println("");
        for(Course c : listOfCourse){
            System.out.println("Course code: "+c.getCourseCode());
            System.out.println("Course name: "+c.getCourseName());
            String courseSem ="";
            for(int i = 0; i < c.getCourseSem().length; i++){
                courseSem = courseSem + c.getCourseSem()[i]+", ";
            }
            System.out.println("Course sem: "+courseSem);
            System.out.println("Course AU: "+c.getCourseAU());
            System.out.println("Course School: "+c.getSchool());
            String courseType ="";
            for(int i = 0; i < c.getCourseType().size(); i++){
                courseType = courseType + c.getCourseType().get(i)+", ";
            }
            System.out.println("Course Type: "+courseType);
            System.out.println("Course Indexes:");
            String courseIndex = "";
            for(int i = 0; i < c.getIndexes().size(); i++){
               courseIndex = courseIndex + c.getIndexes().get(i).getIndexID()+", ";
            }
            System.out.println(courseIndex);
            printDivider();
        }
    }
    /**
     * Interface for user to enter course information
     * @return Course object construction of user input
     */
    public Course getCourseInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("To add new course, ensure that the following information is ready: \n"
                        +"Course name, code, AU, School and type.");
        System.out.println("Key in the details for the new course: ");

        String nameQst = "Enter the name of the course (up to 50 characters, e.g -> Urban Planning): ";
        String courseName = getStrInputWithinRange(nameQst, 1, 50);
        String codeQst = "Enter the course code (6 Characters -> e.g CZ1011): ";
        String courseCode = getStrInputWithinRange(codeQst, 6, 6);
        String auQuestion = "Enter course AU (Between 1 to 10 AU): ";
        int courseAU = getIntInputWithinRange(auQuestion, 1, 10);
        System.out.println("List of schools: ");

        ArrayList<School> listOfSchool = new ArrayList<School>(Arrays.asList(School.values()));
        for (int i = 0; i < listOfSchool.size(); i++) {
            System.out.println(i + 1 + "." + listOfSchool.get(i));
        }
        String schoolQst = "Select course School: ";
        int schoolChoice = getIntInputWithinRange(schoolQst, 1,listOfSchool.size())-1;
        School courseSchool = listOfSchool.get(schoolChoice);

        int courseChoice = 0;
        ArrayList<CourseType> selectedCourseType = new ArrayList<CourseType>();
        ArrayList<CourseType> listOfCourseType = new ArrayList<CourseType>(Arrays.asList(CourseType.values()));
        while (courseChoice!=0||listOfCourseType.size() != 0) {
            System.out.println("List of course type(s): ");
            for (int i = 0; i < listOfCourseType.size(); i++) {
                System.out.println(i + 1 + "." + listOfCourseType.get(i));
            }
            String typeQst = "Select Course type and Enter 0 to Stop: ";
            courseChoice = getIntInputWithinRange(typeQst, 0, listOfCourseType.size())-1;
            if(courseChoice != -1){
                selectedCourseType.add(listOfCourseType.get(courseChoice));
                listOfCourseType.remove(listOfCourseType.get(courseChoice));
            }
            else{break;}
        }

        System.out.println("Select the course availability throughout semesters");
        String[] semOptions = {"First and second semesters","First semester only","Second semester only"};
        int userSemChoice = getUserMultipleOptions(semOptions);

        //Convert to int[] array
        int[] courseSem;
        if (userSemChoice == 1) courseSem = new int[]{1, 2};
        else if (userSemChoice == 2) courseSem = new int[]{1};
        else {
            courseSem = new int[]{2};
        }

        Course courseToBeAdded = new Course(courseCode, courseName, courseSem, courseAU, courseSchool, selectedCourseType);
        return courseToBeAdded;
    }
    /**
     * Interface for staff to enter index information, including creating lessons for Index 
     * @return Index constructed from staff input
     */
    public Index getIndexInfo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Information for Index: ");
        String newIndexQuestion = "Enter the index ID (5 characters, note: must be unique within the Course): ";
        String indexNewID = getStrInputWithinRange(newIndexQuestion,5,5);

        String maxVacanQuestion = "Enter number of max vacancy for index (Max 2000 slots): ";
        int indexMaxVacancy = getIntInput(maxVacanQuestion);

        String groupNameMsg = "Enter group name (Max 6 characters)";
        String groupName = getStrInputWithinRange(groupNameMsg,1,6);

        String numLesQuestion = "Enter number of lessons Index have (Max 5, note: Lessons consists of LEC,TUT,LAB,SEM): ";
        int numOfLessons = getIntInputWithinRange(numLesQuestion, 1, 5);
        ArrayList<Lesson> listOfAddLesson = new ArrayList<Lesson>();

        for (int j = 0; j < numOfLessons; j++) {
            printDivider();
            System.out.println("List of lesson types:");
            ArrayList<LessonType> listOfLessons = new ArrayList<LessonType>(Arrays.asList(LessonType.values()));
            for (int k = 0; k < listOfLessons.size(); k++) {
                System.out.println(k + 1 + "." + listOfLessons.get(k));
            }
            String typeQst = "Select new lesson type: ";
            int lessonChoice = getIntInputWithinRange(typeQst,1,listOfLessons.size())-1;
            LessonType lessonType = listOfLessons.get(lessonChoice);

            VenueManager v = new VenueManager();
            System.out.println("List of venues: ");
            ArrayList<Venue> listOfVenue = v.getAllVenue();
            for (int k = 0; k < listOfVenue.size(); k++) {
                System.out.println(k + 1 + "." + listOfVenue.get(k).getVenueBlockFloorName());
            }
            String venueQst = "Select venue: ";
            int venueChoice = getIntInputWithinRange(venueQst, 1,listOfVenue.size())-1;
            Venue lessonVenue = listOfVenue.get(venueChoice);
            System.out.println("List of day of weeks");
            ArrayList<DayOfWeekStars> dayOfWeekList = new ArrayList<DayOfWeekStars>(Arrays.asList(DayOfWeekStars.values()));
            for (int k = 0; k < dayOfWeekList.size(); k++) {
                System.out.println(k + 1 + "." + dayOfWeekList.get(k));
            }
            String dayQst = "Select Day of Week: ";
            int dayOfWeekChoice = getIntInputWithinRange(dayQst, 1, dayOfWeekList.size())-1;
            DayOfWeekStars lessonDay = dayOfWeekList.get(dayOfWeekChoice);

            System.out.println("List of Start time: ");
            ArrayList<IndexTiming> indexTimingList = new ArrayList<IndexTiming>(Arrays.asList(IndexTiming.values()));
            for (int k = 0; k < indexTimingList.size()-1; k++) {
                System.out.println(k + 1 + "." + indexTimingList.get(k).label);
            }
            String startQst = "Select Start Time: ";
            int startChoice = getIntInputWithinRange(startQst,1,indexTimingList.size()-1)-1;
           // int startChoice = Integer.parseInt(sc.nextLine()) - 1;
            IndexTiming startTime = indexTimingList.get(startChoice);
            System.out.println("List of end time: ");
            for (int k = startChoice+1; k < indexTimingList.size(); k++) {
                System.out.println(k + 1 + "." + indexTimingList.get(k).label);
            }
            String endQst = "Select End Time: ";
            int endChoice = getIntInputWithinRange(endQst, startChoice+1, indexTimingList.size())-1;
           //int endChoice = Integer.parseInt(sc.nextLine()) - 1;
            IndexTiming endTime = indexTimingList.get(endChoice);

            String remarkQst = "Enter any remarks (Max 100 words): ";
            String lessonRemark = getStrInputWithinRange(remarkQst,0,100);

            //convert time to LocalDateTime
            LocalTime startLocalTime = convertIndexTimingToLocalTime(startTime);
            LocalTime endLocalTime = convertIndexTimingToLocalTime(endTime);

            //Create lesson object
            Lesson l1;
            switch (lessonType) {
                case LAB:
                    l1 = new LessonLab(lessonVenue, startLocalTime, endLocalTime, lessonDay, lessonRemark);
                case LECTURE:
                    l1 = new LessonLec(lessonVenue, startLocalTime, endLocalTime, lessonDay, lessonRemark);
                case TUTORIAL:
                    l1 = new LessonTut(lessonVenue, startLocalTime, endLocalTime, lessonDay, lessonRemark);
                default://is SEM
                    l1 = new LessonSem(lessonVenue, startLocalTime, endLocalTime, lessonDay, lessonRemark);
            }
            //System.out.println(l1.toString());
            listOfAddLesson.add(l1);
        }
        Index indexToBeAdded = new Index(indexNewID, indexMaxVacancy, listOfAddLesson, groupName);
        return indexToBeAdded;
    }
    /**
     * Convert the staff input into to a local time object 
     * @param ti IndexTiming enum user selected
     * @return local time object that user selected
     */
    public LocalTime convertIndexTimingToLocalTime(IndexTiming ti){
        switch(ti){
            case EIGHT_THIRTY:
                LocalTime ldt = LocalTime.of(8, 30);
                return ldt;
            case NINE_THIRTY:
                LocalTime ldt1 = LocalTime.of(9, 30);
                return ldt1;
            case TEN_THIRTY:
                LocalTime ldt2 = LocalTime.of(10, 30);
                return ldt2;
            case ELEVEN_THIRTY:
                LocalTime ldt3 = LocalTime.of(11, 30);
                return ldt3;
            case TWELVE_THIRTY:
                LocalTime ldt4 = LocalTime.of(12, 30);
                return ldt4;
            case THIRTEEN_THIRTY:
                LocalTime ldt5 = LocalTime.of(13, 30);
                return ldt5;
            case FOURTEEN_THIRTY:
                LocalTime ldt6 = LocalTime.of(14, 30);
                return ldt6;
            case FIFTEEN_THIRTY:
                LocalTime ldt7 = LocalTime.of(15, 30);
                return ldt7;
            case SIXTEEN_THIRTY:
                LocalTime ldt8 = LocalTime.of(16, 30);
                return ldt8;
            case SEVENTEEN_THIRTY:
                LocalTime ldt9 = LocalTime.of(17, 30);
                return ldt9;
            case EIGHTEEN_THIRTY:
                LocalTime ldt10 = LocalTime.of(18, 30);
                return ldt10;
            case NINETEEN_THIRTY:
                LocalTime ldt11 = LocalTime.of(19, 30);
                return ldt11;
            case TWENTY_THIRTY:
                LocalTime ldt12 = LocalTime.of(20, 30);
                return ldt12;
            case TWENTYONE_THIRTY:
                LocalTime ldt13 = LocalTime.of(21, 30);
                return ldt13;
            default:
                LocalTime ldt14 = LocalTime.of(22, 30);
                return ldt14;
        }

    }
    /**
     * Interface for user to select School option and returns the specifed School object 
     * @return Selected school enum object
     */
    public School getSchool(){
        Scanner sc = new Scanner(System.in);
        System.out.println("List of schools: ");
        ArrayList<School> listOfSchool = new ArrayList<School>(Arrays.asList(School.values()));
        for (int i = 0; i < listOfSchool.size(); i++) {
            System.out.println(i + 1 + "." + listOfSchool.get(i));
        }
        String schoolQst = "Select new Course school: ";
        int schoolChoice = getIntInputWithinRange(schoolQst,1,listOfSchool.size())-1;
        //int schoolChoice = Integer.parseInt(sc.nextLine()) - 1;
        School courseSchool = listOfSchool.get(schoolChoice);
        return courseSchool;
    }
    /**
     * Displays list of index with details, index id, available slots, group name, with lesson details
     * - lesson type, day of week, start and end time and remark
     * @param indexList List of indexes to display
     */
    public void displayIndexes(ArrayList<Index> indexList){
        int i = 1;
        printDivider();
        System.out.println("Indexes are as follows: ");
        for(Index ind : indexList){
            System.out.println(i+". IndexID: "+ind.getIndexID());
            System.out.println("   Index max vacancy: "+ind.getMaxVacancy());
            int availableSlots = ind.getMaxVacancy() - ind.getStudentsRegistered().size();
            if(availableSlots < 1)availableSlots = 0;
            System.out.println("   Index available slots: "+availableSlots);
            System.out.println("   Index groupName: "+ind.getGroupName());
            System.out.println("   List of index lessons: ");
            i++;
            for(Lesson l : ind.getLessonList()){
                System.out.println("    -----------------------------");
                LessonType lessonType = l.getLessonType();
                String day = l.getDayOfWeek()+"";
                String time = l.getStartTime()+" - "+l.getEndTime();
                String remark = l.getRemark();
                System.out.println("    Class Type: "+ lessonType);
                System.out.println("        Day: "+ day);
                System.out.println("        Time: "+ time);
                System.out.println("        Remark: "+remark);
            }
        }
    }
    /**
     * Display one student details - student name, username, password, study year, nationality, gender and program
     * @param s Student object to be printed
     */
    public void displayStudentDetails(Student s){
        System.out.println("The student details are as follows: ");
        System.out.println("Student name: "+s.getName());
        System.out.println("Student UserID: "+s.getUsername());
        String userPassword = new String(s.getPassword());
        System.out.println("Student Password: "+userPassword);
        System.out.println("Student Study Year:"+s.getStudyYear());
        System.out.println("Student Nationality: "+s.getNationality());
        System.out.println("Student Gender: "+s.getGender());
        System.out.println("Student Program: "+s.getProgram());
        System.out.println("");
        Scanner sc = new Scanner(System.in);
    }
    /**
     * Display list of student details - student name, nationality, gender, email, matriculation number, study year and program
     * @param listOfStudent list of students to be printed
     */
    public void displayStudentList(ArrayList<Student> listOfStudent){
        printPageBreak();
        System.out.println("List of students:");
        if(listOfStudent.size() == 0 )System.out.println("List is empty");
         for(Student student : listOfStudent){
             printDivider();
             System.out.println("Student Name:"+student.getName());
             System.out.println("Student Nationality:"+student.getNationality());
             System.out.println("Student Gender:"+student.getGender());
             System.out.println("Student Email:"+student.getEmail());
             System.out.println("Student Matriculation Number:"+student.getMatricNumber());
             System.out.println("Student Study Year:"+student.getStudyYear());
             System.out.println("Student Program:"+student.getProgram());
         }
    }
    /**
     * Display students according to Course - Students name, gender and nationality
     * @param registeredStudents list of students to be printed
     * @param courseCode The course code to be displayed at the header
     */
    public void displayStudentByCourse(ArrayList<Student> registeredStudents, String courseCode){
        System.out.println("List of students taking course: "+courseCode);
        System.out.println("Registered students:");
        printDivider();
        if(registeredStudents.size() == 0) System.out.println("List is empty");
        for(Student student : registeredStudents){
            System.out.println("Student Name: "+student.getName());
            System.out.println("Student Gender: "+student.getGender());
            System.out.println("Student Nationality: "+student.getNationality());
            printDivider();
        }
    }
}
