package Managers;
import Entities.*;
import Enums.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * FileManager contains the logic to coordinate and realise use case 
 */
public class FileManager {
/**
 * Default constructor of FileManager
 */
public FileManager(){}

    /**
     * Read Serialized object from data 
     * @param filename name of file that contains the data
     * @return A list of objects read from the data
     */
    public ArrayList<Object> readSerializedObject(String filename){
        ArrayList<Object> list = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try{
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            list = (ArrayList<Object>) in.readObject();
            in.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * Write Serialized object from data
     * @param filename name of file that contains the data
     * @param list List of object that will be written to the data
     */
    public static void writeSerializedObject(String filename, List list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            //clears existing file
            new FileOutputStream(filename).close();
            fos = new FileOutputStream(filename);
            //fos.write(32);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            //	System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves student data, provides the file name and calls readSerializedObject to read the data
     * @see readSerializedObject
     * @return A list of students from data
     */
    public ArrayList<Student> readStudentData(){
        String filename = "./STARS/src/Data/student.dat";
        ArrayList<Object> list = readSerializedObject(filename);
        ArrayList<Student> listOfStudent = new ArrayList<Student>();
        for(int i = 0; i < list.size(); i++){
            Student s1 = (Student) list.get(i);
            listOfStudent.add(s1);
        }
        return listOfStudent;
    }

    /**
     * Writes student data, provides file name and calls writeSerializedObject to write data
     * @param updatedListOfStudent Array list of updated students to be written
     */
    public void writeStudentData(ArrayList<Student> updatedListOfStudent){
        String filename = "./STARS/src/Data/student.dat";
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < updatedListOfStudent.size(); i++){
            Object o1 = (Object) updatedListOfStudent.get(i);
            list.add(o1);
        }
        writeSerializedObject(filename, list);
    }

    /**
     * Retrieves staff data, provides the file name and calls readSerializedObject to read the data
     * @see readSerializedObject
     * @return A list of staff from data
     */
    public ArrayList<Staff> readStaffData(){
        String filename = "./STARS/src/Data/staff.dat";
        ArrayList<Object> list = readSerializedObject(filename);
        ArrayList<Staff> listOfStaff = new ArrayList<Staff>();
        for(int i = 0; i < list.size(); i++){
            Staff s1 = (Staff) list.get(i);
            listOfStaff.add(s1);
        }
        return listOfStaff;
    }

    /**
     * Writes staff data, provides file name and calls writeSerializedObject to write data
     * @param updatedListOfStaff Array list of updated staff to be written
     */
    public void writeStaffData(ArrayList<Staff> updatedListOfStaff){
        String filename = "./STARS/src/Data/staff.dat";
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < updatedListOfStaff.size(); i++){
            Object o1 = (Object) updatedListOfStaff.get(i);
            list.add(o1);
        }
        writeSerializedObject(filename, list);
    }

    /**
     * Reads course data, provides provides the file name and calls readSerializedObject to read the data
     * @return A list of course from data
     */
    public ArrayList<Course> readCourseData(){
        String filename = "./STARS/src/Data/course.dat";
        ArrayList<Object> list = readSerializedObject(filename);
        ArrayList<Course> listOfCourse = new ArrayList<Course>();
        for(int i = 0; i < list.size(); i++){
            Course c1 = (Course) list.get(i);
            listOfCourse.add(c1);
        }
        return listOfCourse;
    }

    /**
     * Writes course data, provides file name and calls writeSerializedObject to write data
     * @param updatedListOfCourse Array list of updated course to be written
     */
    public void writeCourseData(ArrayList<Course> updatedListOfCourse){
        String filename = "./STARS/src/Data/course.dat";
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < updatedListOfCourse.size(); i++){
            Object o1 = (Object) updatedListOfCourse.get(i);
            list.add(o1);
        }
        writeSerializedObject(filename, list);
    }

    /**
     * Reads sem access period data, provides the file name and calls readSerializedObject to read the data
     * @see SemRegistPeriod
     * @return sem registration period object
     */
    public SemRegistPeriod readSemAccessData(){
        String filename = "./STARS/src/Data/semAccessPeriod.dat";
        ArrayList<Object> list = readSerializedObject(filename);
        SemRegistPeriod srp = (SemRegistPeriod) list.get(0);
        return srp;
    }

    /**
     * Write sem registration period data, provides the file name and calls writeSemAccessData to read the data
     * @param updatedSemPeriod sem registration period object that is going to be written in data
     * @see SemRegistPeriod
     */
    public void writeSemAccessData(SemRegistPeriod updatedSemPeriod){
        String filename = "./STARS/src/Data/semAccessPeriod.dat";
        ArrayList<Object> list = new ArrayList<Object>();
        Object o1 = (Object) updatedSemPeriod;
        list.add(o1);
        writeSerializedObject(filename, list);
    }
    /**
     * Reads venue data, provides file name and calls readSerializedObject to read the data
     * @see Venue
     * @return list of venus from data 
     */
    public ArrayList<Venue> readVenueData(){
        String filename = "./STARS/src/Data/venue.dat";
        ArrayList<Object> list = readSerializedObject(filename);
        ArrayList<Venue> listOfVenue = new ArrayList<Venue>();
        for(int i = 0; i < list.size(); i++){
            Venue v1 = (Venue) list.get(i);
            listOfVenue.add(v1);
        }
        return listOfVenue;
    }
    /**
     * Write venue data, provides the file name and calls writeSemAccessData to read the data
     * @param updatedListOfVenue venue object that is going to be written in data
     * @see Venue
     */
    public void writeVenueData(ArrayList<Venue> updatedListOfVenue){
        String filename = "./STARS/src/Data/venue.dat";
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < updatedListOfVenue.size(); i++){
            Object o1 = (Object)updatedListOfVenue.get(i);
            list.add(o1);
            writeSerializedObject(filename, list);
        }
    }
/**
 * Preloads the original data 
 */
public void preload(){
    //CREATE 15 STUDENTS
    ArrayList<Student> listOfAllStudents = new ArrayList<Student>();
    char[]  password1 = {'1','2','3','4'};
    Student s1 = new Student("C190106", password1, "TEO BENG HENG", Nationality.SINGAPOREAN, Gender.MALE, "U1921884J", 3, Program.CSC, "c190106@e.ntu.edu.sg");
    char[]  password2 = {'a','b','b','e'};
    Student s2 = new Student("C190107", password2, "WYNN MIKE HUNT", Nationality.CANADIAN, Gender.MALE, "U1345233A", 1, Program.CEE, "j002@e.ntu.edu.sg");
    char[]  password3 = {'1','2','3','4'};
    Student s3 = new Student("C190108", password3, "DENZEL CURRY", Nationality.US_AMERICAN, Gender.MALE, "U152984EX", 1, Program.ACC, "CLIM131@e.ntu.edu.sg");
    char[]  password4 = {'1','3','b','2'};
    Student s4 = new Student("C190109", password4, "SNOOP DOGG", Nationality.US_AMERICAN, Gender.MALE, "U1420420N", 1, Program.EEE, "j004@e.ntu.edu.sg");
    char[]  password5 = {'a','b','1','5'};
    Student s5 = new Student("C190100", password5, "CHEN LEI", Nationality.SINGAPOREAN, Gender.MALE, "U1923243J", 1, Program.DSAI, "j005@e.ntu.edu.sg");
    char[]  password6 = {'5','2','z','z'};
    Student s6 = new Student("C190111", password6, "CARDI B TAN", Nationality.BURMESE, Gender.FEMALE, "U1921111J", 1, Program.BIE, "j006@e.ntu.edu.sg");
    char[]  password7 = {'z','4','d','1'};
    Student s7 = new Student("C190123", password7, "SYED HAZIQ BIN SYED ALI", Nationality.SINGAPOREAN, Gender.MALE, "U1921222J", 2, Program.DSAI, "j007@e.ntu.edu.sg");
    char[]  password8 = {'f','g','f','d'};
    Student s8 = new Student("C190124", password8, "CHEW LOH SENG", Nationality.KOREAN, Gender.MALE, "U1921333P", 2, Program.CSC, "j008@e.ntu.edu.sg");
    char[]  password9 = {'1','3','g','g'};
    Student s9 = new Student("C190125", password9, "ROYCE TAN CHUN WEI", Nationality.JAPANESE, Gender.MALE, "U1921444J", 2, Program.CSC, "j009@e.ntu.edu.sg");
    char[]  password10 = {'f','z','z','f'};
    Student s10 = new Student("C190126", password10, "EUNICE CHEW", Nationality.FILIPINO, Gender.FEMALE, "U1921555J", 2, Program.CSC, "j010@e.ntu.edu.sg");
    char[]  password11 = {'r','d','s','e'};
    Student s11 = new Student("C190127", password11, "CHEN SU LI", Nationality.CHINESE, Gender.FEMALE, "U1921666J", 2, Program.EEE, "j011@e.ntu.edu.sg");
    char[]  password12 =  {'r','e','g','d'};
    Student s12 = new Student("C190128", password12, "JENNIFER LAWRENCE", Nationality.INDIAN, Gender.FEMALE, "U1921777J", 1, Program.ENG, "j012@e.ntu.edu.sg");
    char[]  password13 = {'a','h','j','t'};
    Student s13 = new Student("C190129", password13, "CLAUDIA BETH ONG", Nationality.THAI, Gender.FEMALE, "U1921888J", 1, Program.REP, "j013@e.ntu.edu.sg");
    char[]  password14 = {'y','t','f','f'};
    Student s14 = new Student("C190132", password14, "MEGAN TAN", Nationality.MALAYSIAN, Gender.FEMALE, "U1921999J", 3, Program.EEE, "j014@e.ntu.edu.sg");
    char[]  password15 = {'y','r','r','1'};
    Student s15 = new Student("C190122", password15, "AMINAH BINTI SAID", Nationality.SINGAPOREAN, Gender.FEMALE, "U1926969J", 3, Program.CSE, "j015@e.ntu.edu.sg");

    listOfAllStudents.add(s1);listOfAllStudents.add(s2);listOfAllStudents.add(s3);listOfAllStudents.add(s4);listOfAllStudents.add(s5);
    listOfAllStudents.add(s6);listOfAllStudents.add(s7);listOfAllStudents.add(s8);listOfAllStudents.add(s9);listOfAllStudents.add(s10);
    listOfAllStudents.add(s11);listOfAllStudents.add(s12);listOfAllStudents.add(s13);listOfAllStudents.add(s14);listOfAllStudents.add(s15);
    writeStudentData(listOfAllStudents);

    //CREATE 4 COURSES
    School sch1 = School.ADM;
    School sch2 = School.SCSE;
    Venue v1 = new Venue("LT2A Lecture Room","NS-04","LT2A-NS-04-01");
    Venue v2 = new Venue("LT19 Lecture Room","NS-04","LT19-NS-04-01");
    Venue v3 = new Venue("Tutorial Room 19","NS-04","TR-NS-04-05");
    Venue v4 = new Venue("Tutorial Room 18","NS-04","TR-NS-04-05");
    Venue v5 = new Venue("Tutorial Room 17","NS-04","TR-NS-04-05");
    Venue v6 = new Venue("Software Project Lab","NS-04","SPL-NS-04-05");
    Venue v7 = new Venue("Software Lab 1","NS-04","SWL1-NS-04-05");
    String remark = "nil";

    LocalTime early = LocalTime.of(8,30);
    LocalTime kindaEarly = LocalTime.of(10,30);
    LocalTime justNice = LocalTime.of(12,30);
    LocalTime kindaLate = LocalTime.of(14,30);
    LocalTime late = LocalTime.of(16,30);

    int[] sem = {1};
    ArrayList<CourseType> courseTypeCore = new ArrayList<CourseType>();
    courseTypeCore.add(CourseType.CORE);
    ArrayList<CourseType> courseTypeUE = new ArrayList<CourseType>();
    courseTypeUE.add(CourseType.UE);courseTypeUE.add(CourseType.CORE);

    Lesson l1 = new LessonLec(v1, early, kindaEarly, DayOfWeekStars.MONDAY,remark);
    Lesson l2 = new LessonLab(v6, justNice, kindaLate, DayOfWeekStars.THURSDAY,remark);
    ArrayList<Lesson> lessonList1 = new ArrayList<Lesson>();
    lessonList1.add(l1);lessonList1.add(l2);

    Lesson l3 = new LessonLec(v1, early, kindaEarly, DayOfWeekStars.MONDAY, remark);
    Lesson l4 = new LessonLab(v6, justNice, kindaLate, DayOfWeekStars.TUESDAY,remark);
    ArrayList<Lesson> lessonList2 = new ArrayList<Lesson>();
    lessonList2.add(l3);lessonList2.add(l4);

    Lesson l5 = new LessonLec(v1, early, kindaEarly, DayOfWeekStars.MONDAY, remark);
    Lesson l6 = new LessonLab(v7, justNice, kindaLate, DayOfWeekStars.FRIDAY,remark);
    ArrayList<Lesson> lessonList3 = new ArrayList<Lesson>();
    lessonList3.add(l5);lessonList3.add(l6);

    Index i1 = new Index("11023",5, lessonList1, "SSP6");
    Index i2 = new Index("11024",5, lessonList2, "SS6");
    Index i3 = new Index("11025",5, lessonList3, "S66");
    ArrayList<Index> index1 = new ArrayList<Index>();
    index1.add(i1);index1.add(i2);index1.add(i3);

    Lesson l7 = new LessonLec(v2, early, justNice, DayOfWeekStars.TUESDAY, remark);
    Lesson l8 = new LessonTut(v3, kindaLate, late, DayOfWeekStars.WEDNESDAY,remark);
    ArrayList<Lesson> lessonList4 = new ArrayList<Lesson>();
    lessonList4.add(l7);lessonList4.add(l8);

    Lesson l9 = new LessonLec(v2, early, justNice, DayOfWeekStars.TUESDAY, remark);
    Lesson l10 = new LessonLab(v6, kindaLate, late, DayOfWeekStars.FRIDAY,remark);
    Lesson l11 = new LessonTut(v4, justNice, kindaLate, DayOfWeekStars.MONDAY,remark);
    ArrayList<Lesson> lessonList5 = new ArrayList<Lesson>();
    lessonList5.add(l9);lessonList5.add(l10);lessonList5.add(l11);

    Lesson l12 = new LessonLec(v2, early, justNice, DayOfWeekStars.TUESDAY, remark);
    Lesson l13 = new LessonTut(v4, justNice, kindaLate, DayOfWeekStars.MONDAY,remark);
    ArrayList<Lesson> lessonList6 = new ArrayList<Lesson>();
    lessonList6.add(l12);lessonList6.add(l13);

    Index i4 = new Index("11026",5, lessonList4, "SSP6");
    Index i5 = new Index("11027",5, lessonList5, "SS6");
    Index i6 = new Index("11028",5, lessonList6, "S66");
    ArrayList<Index> index2 = new ArrayList<Index>();
    index2.add(i4);index2.add(i5);index2.add(i6);

    Lesson l14 = new LessonLec(v2, justNice, kindaLate, DayOfWeekStars.MONDAY, remark);
    Lesson l15 = new LessonLab(v7, justNice, kindaLate, DayOfWeekStars.TUESDAY,remark);
    Lesson l16 = new LessonTut(v5, early, kindaEarly, DayOfWeekStars.TUESDAY,remark);
    ArrayList<Lesson> lessonList7 = new ArrayList<Lesson>();
    lessonList7.add(l14);lessonList7.add(l15);lessonList7.add(l16);

    Lesson l17 = new LessonLec(v2, justNice, kindaLate, DayOfWeekStars.MONDAY, remark);
    Lesson l18 = new LessonLab(v6, kindaLate, late, DayOfWeekStars.MONDAY,remark);
    Lesson l19 = new LessonTut(v5, kindaEarly, justNice, DayOfWeekStars.FRIDAY,remark);
    ArrayList<Lesson> lessonList8 = new ArrayList<Lesson>();
    lessonList8.add(l17);lessonList8.add(l18);lessonList8.add(l19);

    Lesson l20 = new LessonLec(v2, justNice, kindaLate, DayOfWeekStars.MONDAY, remark);
    Lesson l21 = new LessonLab(v7, kindaLate, late, DayOfWeekStars.TUESDAY,remark);
    Lesson l22 = new LessonTut(v5, kindaLate, late, DayOfWeekStars.FRIDAY,remark);
    ArrayList<Lesson> lessonList9 = new ArrayList<Lesson>();
    lessonList9.add(l20);lessonList9.add(l21);lessonList9.add(l22);

    Index i7 = new Index("11029",5, lessonList7, "SSP6");
    Index i8 = new Index("11030",3, lessonList8, "SS6");
    Index i9 = new Index("11031",5, lessonList9, "S66");
    ArrayList<Index> index3 = new ArrayList<Index>();
    index3.add(i7);index3.add(i8);index3.add(i9);

    Lesson l23 = new LessonLec(v1, kindaEarly, kindaLate, DayOfWeekStars.THURSDAY, remark);
    Lesson l24 = new LessonLab(v7, justNice, kindaLate, DayOfWeekStars.TUESDAY,remark);
    Lesson l25 = new LessonTut(v3, justNice, kindaLate, DayOfWeekStars.WEDNESDAY,remark);
    ArrayList<Lesson> lessonList10 = new ArrayList<Lesson>();
    lessonList10.add(l23);lessonList10.add(l24);lessonList10.add(l25);

    Lesson l26 = new LessonLec(v1, kindaEarly, kindaLate, DayOfWeekStars.THURSDAY, remark);
    Lesson l27 = new LessonLab(v7, justNice, kindaLate, DayOfWeekStars.TUESDAY,remark);
    Lesson l28 = new LessonTut(v3, early, kindaEarly, DayOfWeekStars.FRIDAY,remark);
    ArrayList<Lesson> lessonList11 = new ArrayList<Lesson>();
    lessonList11.add(l26);lessonList10.add(l27);lessonList10.add(l28);

    Index i10 = new Index("11032",5, lessonList10, "SSP6");
    Index i11 = new Index("11033",3, lessonList11, "SS6");
    ArrayList<Index> index4 = new ArrayList<Index>();
    index4.add(i10);index4.add(i11);

    Course c1 = new Course("CZ3001", "Advanced Computer Architecture", sem,3, sch1, courseTypeCore);
    Course c2 = new Course("CB2001", "Algorithms", sem,3, sch2, courseTypeCore);
    Course c3 = new Course("CB2007", "Introduction to Database", sem,3, sch1, courseTypeUE);
    Course c4 = new Course("CB3001", "Enterprise and Innovation", sem,3, sch1, courseTypeUE);

    ArrayList<String> studentList1 = new ArrayList<String>();
    studentList1.add(s1.getMatricNumber());
    studentList1.add(s2.getMatricNumber());
    studentList1.add(s3.getMatricNumber());
    studentList1.add(s4.getMatricNumber());
    ArrayList<String> studentList1WithoutBeng = new ArrayList<String>();
    studentList1WithoutBeng.add(s2.getMatricNumber());
    studentList1WithoutBeng.add(s3.getMatricNumber());
    studentList1WithoutBeng.add(s4.getMatricNumber());

    ArrayList<String> studentList2 = new ArrayList<String>();
    studentList2.add(s3.getMatricNumber());
    studentList2.add(s6.getMatricNumber());
    studentList2.add(s7.getMatricNumber());
    studentList2.add(s8.getMatricNumber());

    ArrayList<String> studentList2WithBeng = new ArrayList<String>();
    studentList2WithBeng.add(s1.getMatricNumber());
    studentList2WithBeng.add(s3.getMatricNumber());
    studentList2WithBeng.add(s6.getMatricNumber());
    studentList2WithBeng.add(s7.getMatricNumber());
    studentList2WithBeng.add(s8.getMatricNumber());

    ArrayList<String> studentList3 = new ArrayList<String>();
    studentList3.add(s9.getMatricNumber());
    studentList3.add(s10.getMatricNumber());
    studentList3.add(s11.getMatricNumber());
    studentList3.add(s12.getMatricNumber());

    //11023
    ArrayList<String> studentList11023 = (ArrayList<String>)studentList1.clone();
    index1.get(0).setStudentsRegistered(studentList11023);
    //11024
    ArrayList<String> studentList11024 = (ArrayList<String>)studentList2.clone();
    index1.get(1).setStudentsRegistered(studentList11024);
    //11025
    ArrayList<String> studentList11025 = (ArrayList<String>)studentList3.clone();
    index1.get(2).setStudentsRegistered(studentList11025);

    //11026
    ArrayList<String> studentList11026 = (ArrayList<String>)studentList3.clone();
    index2.get(0).setStudentsRegistered(studentList3);
    //11027
    ArrayList<String> studentList11027 = (ArrayList<String>)studentList1WithoutBeng.clone();
    index2.get(1).setStudentsRegistered(studentList11027);
    //11028
    ArrayList<String> studentList11028 = (ArrayList<String>)studentList2.clone();
    index2.get(2).setStudentsRegistered(studentList11028);

    //11029
    ArrayList<String> studentList11029 = (ArrayList<String>)studentList2WithBeng.clone();
    index3.get(0).setStudentsRegistered(studentList11029);
    //11030
    ArrayList<String> studentList11030 = (ArrayList<String>)studentList3.clone();
    index3.get(1).setStudentsRegistered(studentList11030);
    //11031
    ArrayList<String> studentList11031 = (ArrayList<String>)studentList1WithoutBeng.clone();
    index3.get(2).setStudentsRegistered(studentList11031);

    //11032
    ArrayList<String> studentList11032 = (ArrayList<String>)studentList2.clone();
    index4.get(0).setStudentsRegistered(studentList11032);
    //11033
    ArrayList<String> studentList11033 = (ArrayList<String>)studentList1WithoutBeng.clone();
    index4.get(1).setStudentsRegistered(studentList11033);

    c1.setIndexes(index1);
    c2.setIndexes(index2);
    c3.setIndexes(index3);
    c4.setIndexes(index4);

        ArrayList<Course> listOfCourse = new ArrayList<Course>();
        listOfCourse.add(c1);listOfCourse.add(c2);listOfCourse.add(c3);listOfCourse.add(c4);

    //CREATE 3 STAFF
    Staff staff = new Staff("MC0123", password1, "Beng Khoo",Nationality.SINGAPOREAN,Gender.FEMALE, "U1932342", "w102@staff.e.ntu.edu.sg");
    Staff staff1 = new Staff("MC0124", password2, "Rick Ross",Nationality.SINGAPOREAN,Gender.MALE, "U1932111", "w103@staff.e.ntu.edu.sg");
    Staff staff2 = new Staff("MC0125", password3, "Gojou Satoru",Nationality.JAPANESE,Gender.MALE, "U1932222", "w104@staff.e.ntu.edu.sg");
    ArrayList<Staff> listOfStaff = new ArrayList<Staff>();
    listOfStaff.add(staff);listOfStaff.add(staff1);listOfStaff.add(staff2);

    //CREATE REGISTRATION PERIOD
    LocalDateTime ldt1 = LocalDateTime.of(1999,11,18,0,0);
    LocalDateTime ldt2 = LocalDateTime.of(1999,12,1,0,0);
    SemRegistPeriod srp = new SemRegistPeriod(ldt1,ldt2);

    //CREATE 7 VENUES
    ArrayList<Venue> listOfVenue = new ArrayList<Venue>();
    listOfVenue.add(v1);listOfVenue.add(v2);listOfVenue.add(v3);listOfVenue.add(v7);
    listOfVenue.add(v4);listOfVenue.add(v5);listOfVenue.add(v6);

    writeStudentData(listOfAllStudents);
    writeCourseData(listOfCourse);
    writeStaffData(listOfStaff);
    writeVenueData(listOfVenue);
    writeSemAccessData(srp);
}


}
