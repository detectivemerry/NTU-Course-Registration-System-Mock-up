package UserInterface;
import Entities.*;
import Enums.CourseType;
import Enums.IndexStatus;
import Enums.LessonType;
import Managers.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
/**
 * StudentUI handles the Interaction between System and Students
 */
public class StudentUI extends MainUI{
    Student student;
    /**
     * Creates a StudentUI object with student attribute
     * @param student object here will provide the details for the page header
     */
    public StudentUI(Student student){
        this.student = student;
    }
    /**
     * Default constructor for StudentUI
     */
    public StudentUI(){}

    /**
     * Displays the main header and Retrieves student input to run respective methods
     */
    @Override
    public int mainPage() {
        fullStudentHeader();
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
                "1. Add course",
                "2. Drop course",
                "3. Check/Print Courses registered",
                "4. Check Index details(Vacancies available and time slot)",
                "5. Change index number of course",
                "6. Swop Index Number with Another Student",
                "0. Exit Program"
        };
        return options;
    }
    /**
     * Displays header for Student
     */
    @Override
    public void getPageHeader() {
        String studentName = "Name: "+ student.getUsername();
        System.out.println("|-----------------------------------------------------------------------------------------------------------|\n" +
                "|                                                                                                           |\n" +
                "|"+addSpace("STARS")+
                "|"+addSpace("")+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|\n"+
                "|"+addSpace(studentName)+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|");

    }
    /**
     * Displays a full header for student with all information
     */
    public void fullStudentHeader(){
        String studentName = "Name: "+student.getUsername();
        String matriculationNo = "Matric Number: "+student.getMatricNumber();
        String studentProgram = "Current program: "+student.getProgram();
        String studyYear = "Study Year: "+student.getStudyYear();
        System.out.println("|-----------------------------------------------------------------------------------------------------------|\n" +
                "|                                                                                                           |\n" +
                "|"+addSpace("STARS")+"|\n"+
                "|"+addSpace("")+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|\n"+
                "|"+addSpace(studentName)+"|\n" +
                "|"+addSpace(matriculationNo)+"|\n" +
                "|"+addSpace(studentProgram)+"|\n" +
                "|"+addSpace(studyYear)+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|");
    }

   /**
    * Displays index name with course details according to student
    * @param coursesAndIndexTakenByStudent hashmap of course and index to be displayed
    */
    public void displayIndexNameWithCourse(HashMap<Course, Index> coursesAndIndexTakenByStudent){
        String userInput;
        CourseManager courseManager = new CourseManager();
        getPageHeader();
        int displayCounter = 0;
        //Print all indexes that user is registered or in waitlist for
        for(Course c : coursesAndIndexTakenByStudent.keySet()){
            displayCounter++;
            Index ind = coursesAndIndexTakenByStudent.get(c);
            System.out.println(displayCounter+". Course Code:  "+c.getCourseCode());
            System.out.println("   Index Number: "+ind.getIndexID());
            IndexStatus indexStatus = courseManager.getCourseRegistrationStatus(ind.getIndexID(), student.getMatricNumber());
            System.out.println("   Index Status: "+ indexStatus);
            System.out.println("");
        }
    }
    /**
     * Print the course details only
     * @param coursesAndIndexTakenByStudent hashmap of course and index to be displayed according to student specified in matric number
     * @param matricNumber Matric Number of student
     */
    public void printCourseDisplay(HashMap<Course, Index> coursesAndIndexTakenByStudent, String matricNumber){
        System.out.println("");
        CourseManager cm = new CourseManager();
        int counter = 1;
        for(Course c : coursesAndIndexTakenByStudent.keySet()){
            String courseName = c.getCourseName();
            int courseAU = c.getCourseAU();
            ArrayList<CourseType> listofCourse = c.getCourseType();
            String indexID = coursesAndIndexTakenByStudent.get(c).getIndexID();
            IndexStatus status = cm.getCourseRegistrationStatus(indexID, matricNumber);

            System.out.println(counter+".Course Name: "+courseName);
            System.out.println("  AU         : "+courseAU);
            String courseType ="";
            for(int i = 0; i < c.getCourseType().size(); i++){
                courseType = courseType + c.getCourseType().get(i)+"";
            }
            System.out.println("  Course Type: "+courseType);
            System.out.println("  Index      : "+indexID);
            System.out.println("  Status     : "+status);
            System.out.println("");
            counter++;
        }
    }

    /**
     * Display interface to retrieve two indexes ID from user
     * @return list of Index ID
     */
    public String[] changeIndexNoOfCourseGetIndex(){
        System.out.println("You have selected Change Index of Course");
        String[] indexArr = new String[2];
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("Insert Current Index Number:");
            indexArr[0] = sc.nextLine();
            System.out.println("Insert New Index Number:");
            indexArr[1] = sc.nextLine();
        }while(!checkInputInt(indexArr[0]) || !checkInputInt(indexArr[1]));

        return indexArr;
    }
    /**
     * Display Course and Index details
     * @param indexCourseHashMap Hashmap of course and index to be displayed
     */
    public void displayChangeIndex(HashMap<Course, Index> indexCourseHashMap){
        String indexType[] = {"Current", "New"};
        int count = 0;
        //Print both indexes
        for(Course c : indexCourseHashMap.keySet()){
            for(Index ind : c.getIndexes()){
                if(ind.getIndexID() == indexCourseHashMap.get(c).getIndexID()){
                    System.out.println(indexType[count]+"Index Information: ");
                    System.out.println("Course: "+ c.getCourseName());
                    for(Lesson l : ind.getLessonList()){
                        System.out.println("Class Type: "+ l.getLessonType());
                        System.out.println("    Group: "+ ind.getGroupName());
                        System.out.println("    Time: "+ l.getStartTime()+" - "+l.getEndTime());
                        System.out.println("    Venue: "+ l.getVenue().getVenueBlockFloorName());
                        System.out.println("    Remark: "+l.getRemark());
                    }
                }
            }
            count++;
        }
    }
    /**
     * Display interface to retrieve input from user to swop index
     * @see SwopIndexInfo
     * @return SwopIndexInfo object with information 
     */
    public SwopIndexInfo swopIndexWithStudentGetInfo(){
        Scanner sc = new Scanner(System.in);
        String currentIndex, peerUsername, peerIndex;
        char[] peerPassword;
        do{
            System.out.println("You have selected Swop index with student.");
            System.out.println("Your Matric: " + student.getMatricNumber());
            System.out.println("Enter your Index Number: ");
            currentIndex = sc.nextLine();
            System.out.println("Enter Peer username: ");
            peerUsername = sc.nextLine();
            Console console = System.console();
            peerPassword = console.readPassword("Enter peer password: ");
            System.out.println("Enter peer Index Number: ");
            peerIndex = sc.nextLine();
        }while(!checkInputInt(currentIndex) || !checkInputInt(peerIndex));

        SwopIndexInfo si = new SwopIndexInfo(currentIndex, peerUsername, peerPassword, peerIndex);
        return si;
    }
    /**
     * Display Course and Index details in order of current student followed by peer student
     * @param userMatric matric numbers from current student and peer student
     * @param indexCourseInfo Linked hash map with selected course and index objects
     */
    public void displaySwopStudentCourseInfo(String userMatric[], LinkedHashMap<Course, Index> indexCourseInfo){
        int count = 1;
        for(Course c : indexCourseInfo.keySet()){
            if(count==1){
                printPageBreak();
                System.out.println("Course Name: "+c.getCourseName());
                System.out.println("Course Code: "+c.getCourseCode());
            }
            printDivider();
            Index ind = indexCourseInfo.get(c);
            System.out.println("Student #"+count);
            System.out.println("Matric: "+userMatric[count-1]);
            System.out.println("Index Number: "+ind.getIndexID());
            for(Lesson l : ind.getLessonList()){
                System.out.println("Class Type: "+ l.getLessonType());
                System.out.println("    Group: "+ ind.getGroupName());
                System.out.println("    Day: "+l.getDayOfWeek());
                System.out.println("    Time: "+ l.getStartTime()+" - "+l.getEndTime());
                System.out.println("    Venue: "+ l.getVenue().getVenueBlockFloorName());
                System.out.println("    Remark: "+l.getRemark());
            }
            count++;

        }
    }
    /**
     * Displays detailed index information with course details
     * @param indexInfo Hash map with selected course and index objects
     */
    public void displayIndexWithCourse(HashMap<Course, Index> indexInfo){
        int displayCounter = 1;

        for(Course c : indexInfo.keySet()){
            System.out.println("Course code: "+c.getCourseCode());

            printDivider();
            System.out.println("Indexes are as follows: ");
            Index ind = indexInfo.get(c);
            System.out.println(displayCounter+". IndexID: "+ind.getIndexID());
            System.out.println("   Index max vacancy: "+ind.getMaxVacancy());
            int availableSlots = ind.getMaxVacancy() - ind.getStudentsRegistered().size();
            if(availableSlots < 1)availableSlots = 0;
            System.out.println("   Index available slots: "+availableSlots);
            System.out.println("   Index groupName: "+ind.getGroupName());

            System.out.println("   List of index lessons: ");
            for(Lesson l : ind.getLessonList()){
                System.out.println("    -----------------------------");
                LessonType lessonType = l.getLessonType();
                String day = l.getDayOfWeek()+"";
                String time = l.getStartTime()+" - "+l.getEndTime();
                String remark = l.getRemark();
                System.out.println("    Class Type: "+ lessonType);
                System.out.println("    Day: "+ day);
                System.out.println("    Time: "+ time);
                System.out.println("    Remark: "+remark);
            }

        }
        displayCounter++;

    }



}
