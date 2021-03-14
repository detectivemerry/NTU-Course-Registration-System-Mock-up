package Managers;
import Enums.IndexStatus;
import UserInterface.*;
import java.time.LocalTime;
import Entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import API.NotificationAPI;
import API.NotificationEmail;
/**
 * CourseManager contains the logic to coordinate and realise use case
 */
public class CourseManager {
    ErrorMessage em = new ErrorMessage();
    /**
     * Retrieves the current course list from the data file
     * @see FileManager
     * @return course list containing all courses from file
     */
    public ArrayList<Course> getAllCourses(){
        FileManager fm = new FileManager();
        ArrayList<Course> courseList = fm.readCourseData();
        return courseList;
    }
    /**
     * Retrieves the current Index list from the courses in data file
     * @see Course
     * @see Index
     * @return index list containing all indexes in data file
     */
    public ArrayList<Index> getAllIndexes(){
        FileManager fm = new FileManager();
        ArrayList<Course> courseList = fm.readCourseData();
        ArrayList<Index> listOfAllIndexes = new ArrayList<Index>();
        for(Course c : courseList){
            for(Index ind : c.getIndexes()){
                listOfAllIndexes.add(ind);
            }
        }
        return listOfAllIndexes;

    }
    /**
     * Retrieves a hash map of course and index of student
     * @param matricNumber matric number of student 
     * @see Course
     * @see Index
     * @return hash map of course and index of student
     */
    public HashMap<Course, Index> getRegisteredCourseAndIndex(String matricNumber) {
        //Get All courses from database
        ArrayList<Course> courseList = getAllCourses();

        //Initialize hashmap
        HashMap<Course, Index> indexCourseInfo = new HashMap<Course, Index>();

        //Find all courses and the respective index registered by user
        for (Course c : courseList) {
            for (Index ind : c.getIndexes()) {
                ArrayList<String> listOfStudents = ind.getStudentsRegistered();
                for (String s : listOfStudents) {
                    //If user matricNumber is found registered in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseInfo.put(c, ind);
                    }

                }
            }
        }
        return indexCourseInfo;
    }

    /**
     * Retrieves the a hash map of courses that user is registered, waitlist or exempted in
     * @param matricNumber matric number of student
     * @see Course
     * @see Index
     * @return hash map of courses and index taken by student
     */
    public HashMap<Course, Index> getRegisteredOrWaitListOrExemptedCourseAndIndex(String matricNumber) {
        //Get All courses from database
        ArrayList<Course> courseList = getAllCourses();

        //Initialize hashmap
        HashMap<Course, Index> indexCourseHashMap = new HashMap<Course, Index>();
        //Find all courses and the respective index registered by user
        for (Course c : courseList) {
            for (Index ind : c.getIndexes()) {
                ArrayList<String> listOfRegisteredStudents = ind.getStudentsRegistered();
                ArrayList<String> listOfWaitListStudents = ind.getStudentsInWaitLists();
                ArrayList<String> listOfExemptedStudents = ind.getStudentsExempted();
                for (String s : listOfRegisteredStudents) {
                    //If user matricNumber is found registered in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseHashMap.put(c, ind);
                    }
                }
                for(String s : listOfWaitListStudents){
                    //If user matricNumber is found in waitlist in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseHashMap.put(c, ind);
                    }
                }
                for(String s : listOfExemptedStudents){
                    //If user matricNumber is found in exempted in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseHashMap.put(c, ind);
                    }
                }
            }
        }

        return indexCourseHashMap;
    }

    /**
     * Retrieves the a hash map of courses that user is registered or waitlist in
     * @param matricNumber matric number of student
     * @see Course
     * @see Index
     * @return hash map of courses and index taken by student
     */
    public HashMap<Course, Index> getRegisteredOrWaitListCourseAndIndex(String matricNumber) {
        //Get All courses from database
        ArrayList<Course> courseList = getAllCourses();

        //Initialize hashmap
        HashMap<Course, Index> indexCourseHashMap = new HashMap<Course, Index>();

        //Find all courses and the respective index registered by user
        for (Course c : courseList) {
            for (Index ind : c.getIndexes()) {
                ArrayList<String> listOfRegisteredStudents = ind.getStudentsRegistered();
                ArrayList<String> listOfWaitListStudents = ind.getStudentsInWaitLists();
                for (String s : listOfRegisteredStudents) {
                    //If user matricNumber is found registered in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseHashMap.put(c, ind);
                    }
                }
                for(String s : listOfWaitListStudents){
                    //If user matricNumber is found in waitlist in Course c AND Index ind
                    if (s.equals(matricNumber)) {
                        indexCourseHashMap.put(c, ind);
                    }
                }
            }
        }


        return indexCourseHashMap;
    }

      /**
     * Retrieves a list of courses that user is registered or waitlist in
     * @param matricNumber matric number of student
     * @see Course
     * @return list of courses and index taken by student
     */
    public ArrayList<Course> getRegisteredOrWaitListCoursesByStudent(String matricNumber) {
        //Get All courses from database
        ArrayList<Course> courseList = getAllCourses();
        ArrayList<Course> coursesByStudent = new ArrayList<Course>();

        //Whenever the course has a student with same matricNumber, add into list
        for (Course course : courseList) {
            for (int i = 0; i < course.getIndexes().size(); i++) {

                Index ind = course.getIndexes().get(i);
                //If user is registered in this index
                for (String s : ind.getStudentsRegistered()) {
                    //If student is in this index, add course to list
                    if (s.equals(matricNumber)) {
                        coursesByStudent.add(course);
                    }
                }
                //If user is in the waitlist for this index
                for (String s : ind.getStudentsInWaitLists()) {
                    //If student is in this index, add course to list
                    if (s.equals(matricNumber)) {
                        coursesByStudent.add(course);
                    }
                }

            }
        }

        return courseList;
    }

    /**
     * Retrieves list of indexes that user is registered in
     * @param matricNumber mastric number of student
     * @see Index
     * @return list of indexes taken by student
     */
    public ArrayList<Index> getIndexesByStudent(String matricNumber) {
        //Get All courses from database
        ArrayList<Course> courseList = getAllCourses();
        ArrayList<Index> indexesByStudent = new ArrayList<Index>();

        //Whenever the course has a student with same matricNumber, add into lis
        for (Course course : courseList) {
            for (int i = 0; i < course.getIndexes().size(); i++) {
                for (String s : course.getIndexes().get(i).getStudentsRegistered()) {
                    //If student is in this index, add course to list
                    if (s.equals(matricNumber)) {
                        indexesByStudent.add(course.getIndexes().get(i));
                    }
                }
            }
        }
        return indexesByStudent;
    }

    /**
     * Retrieves the Index status of Index for student, either REGISTERED, WAITLIST or EXEMPTED
     * @param indexID index id of course to check
     * @param matricNumber matric number of student
     * @see IndexStatus
     * @return Index status of index for student
     */
    public IndexStatus getCourseRegistrationStatus(String indexID, String matricNumber) {
        //Get All courses from database
        FileManager fm = new FileManager();
        ArrayList<Course> courseList = fm.readCourseData();
        ArrayList<Index> indexesByStudent = new ArrayList<Index>();

        //Whenever the course has a student with same matricNumber, add into list
        for (Course course : courseList) {
            //For each index, search for the selected Index ID.
            for (int i = 0; i < course.getIndexes().size(); i++) {
                Index ind = course.getIndexes().get(i);
                //Index ID is found
                if (ind.getIndexID().equals(indexID)) {
                    //Check if user is registered
                    if (ind.getStudentsRegistered().contains(matricNumber)) {
                        return IndexStatus.REGISTERED;
                    }
                    //Check if user is in wait list
                    else if (ind.getStudentsInWaitLists().contains(matricNumber)) {
                        return IndexStatus.WAITLIST;
                    }
                    //Check if user is exempted
                    else if (ind.getStudentsExempted().contains(matricNumber)) {
                        return IndexStatus.EXEMPTED;
                    }
                }
            }
        }

        //Else, user is not registered
        return IndexStatus.NOT_REGISTERED;
    }

    /**
     * Retrieves the total amount of students registered, in waitlist or exempted of index
     * @param indexID index ID of index to get size 
     * @return the total amount of students in index
     */
    public int getTotalIndexSize(Index indexID) {
        int sizeOfRegistered = indexID.getStudentsRegistered().size();
        int sizeOfWaitList = indexID.getStudentsInWaitLists().size();
        int sizeOfExempted = indexID.getStudentsExempted().size();
        return sizeOfRegistered + sizeOfWaitList + sizeOfExempted;
    }
    
    /**
     * Drops the student from course, and adds a student, if any, from waitlist to course
     * @param indexToBeDropped index ID of index to be dropped
     * @param matricNumber matric number of student
     * @return true if drop was successful, false if drop was not successful
     */
    public boolean dropStudentFromCourse(Index indexToBeDropped, String matricNumber) {
        FileManager fm = new FileManager();
        CourseUI cui = new CourseUI();
        HashMap<Course, Index> registeredCourses = getRegisteredCourseAndIndex(matricNumber);
        ArrayList<Course> updatedCourse = new ArrayList<Course>();

        boolean hasBeenDropped = false;
        for (Course c : registeredCourses.keySet()) {
            for (Index ind : c.getIndexes()) {
                //Drop student matricNumber from index registered list
                if (ind.getIndexID().equals(indexToBeDropped.getIndexID())) {
                    ind.getStudentsRegistered().remove(matricNumber);
                    //Add student from waiting list to registered
                    ind = deQueueFromWaitList(ind);
                    hasBeenDropped = true;
//                    Index updatedIndex = deQueueFromWaitList(ind);
//                    ind = updatedIndex.copyObject();
                }
            }
            updatedCourse.add(c);
        }
        //SAVE TO DATABASE
        GeneralMessage im = new GeneralMessage();
        fm.writeCourseData(updatedCourse);
        if(hasBeenDropped)cui.printInfoMsg(im.SUCCESS, "The course has been dropped.");
        else{
            cui.printInfoMsg(im.FAILURE, "This course has not been dropped successfully.");
        }
        return hasBeenDropped;
    }

    /**
     * Removes a student from wait list, if any, from waitlist and sends email to notify student
     * @param ind Index object that needs to remove a student from wait lits
     * @see NotificationAPI
     * @return Index object with student removed if changed, or original Index object if unchanged
     */
    public Index deQueueFromWaitList(Index ind) {
        //check if there is vacancy and there is a waitlist
        if(ind.getMaxVacancy() >= ind.getStudentsRegistered().size()){
            if(ind.getStudentsInWaitLists().size()>0){
                StudentManager sm = new StudentManager();
                String matricNumber = ind.getStudentsInWaitLists().get(0);
                ind.getStudentsRegistered().add(matricNumber);
                ind.getStudentsInWaitLists().remove(matricNumber);
                
                //Send email
                NotificationAPI ne = new NotificationEmail();
                Student se = sm.getStudentFromMatric(matricNumber);
                Course course = getCourseWithIndex(ind.getIndexID());
                ArrayList<Course> cse = new ArrayList<Course>();
                cse.add(course);
                ArrayList<String> indexList = new ArrayList<String>();
                indexList.add(ind.getIndexID());
                ne.sendRegSuccess(se, cse, indexList);
            }
        }
        return ind;
    }

    /**
     * Retrieves course object that has the Index specified with index ID
     * @param indexID Index ID of index
     * @return Course object that Index belongs in
     */
    public Course getCourseWithIndex(String indexID){
        Course c1 = new Course();
        ArrayList<Course> allCourseList = getAllCourses();
        for(Course c : allCourseList){
            for(Index ind : c.getIndexes()){
                if(ind.getIndexID().equals(indexID)){
                    return c;
                }
            }
        }
        return c1;
    }
    /**
     * Retrieves course object that has the course code specified
     * @param courseCode course code of Course
     * @return Course object with course code
     */
    public Course getCourseWithCourseCode(String courseCode){
        Course emptyCourse = new Course();
        ArrayList<Course> allCourseList = getAllCourses();
        for(Course c : allCourseList){
            if(c.getCourseCode().equals(courseCode)){
                return c;
            }
        }
        return emptyCourse;
    }
    
    /**
     * Retrieves index vacancy for index specified with index ID
     * @param indexID Index ID of index
     * @see Course
     * @see Index
     * @return number of vacancy of index
     */
    public int getIndexVacancy(String indexID){
        int count = 0;
        ArrayList<Course> allCourseList = getAllCourses();
        for(Course c : allCourseList){
            for(Index ind : c.getIndexes()){
                if(ind.getIndexID().equals(indexID)){
                    count = ind.getMaxVacancy() - ind.getStudentsRegistered().size();
                }
            }
        }
        return count;
    }

    /**
     * Check for given index, if the index is vacant
     * @param indexID Index ID of index
     * @return true if Index is vacant, false if Index is not vacant
     */
    public boolean indexIsVacant(String indexID){
        if(getIndexVacancy(indexID) <= 0)return false;
        return true;
    }

    //Check for given index, if will clash if all other registered courses
    /**
     * Check if new index will have a clash in user's current time table
     * @param newIndexID IndexID of the index to check
     * @param matricNumber matric number of student that is checking
     * @return true if there is a clash, false if there are no clashes
     */
    public boolean checkIfIndexClash(String newIndexID, String matricNumber){
        ErrorMessage em = new ErrorMessage();
        HashMap<Course, Index> registeredCourseAndIndex = getRegisteredCourseAndIndex(matricNumber);
        Index newIndex = getIndexFromID(newIndexID);

        //Check for each registered course, each Index and each lesson, that time slot does not clash
        for(Course c : registeredCourseAndIndex.keySet()){
            for(Index currentIndex : c.getIndexes()){
                for(Lesson currentLesson : currentIndex.getLessonList()){
                    for(Lesson newLesson : newIndex.getLessonList()){
                        //If index time falls on the same day
                        if(currentLesson.getDayOfWeek().equals(newLesson.getDayOfWeek())){
                            // l2 cannot be within l1 timing
                            //if l1 start time ---------- l2 start time-------------l1 end time
                            //or if l1 start time ------------l2 end time -------------l1 end time
                            //or l1 start time
                            //   l2 start time
                            LocalTime l1StartTime = currentLesson.getStartTime();
                            LocalTime l1EndTime = currentLesson.getEndTime();
                            LocalTime l2StartTime = newLesson.getStartTime();
                            LocalTime l2EndTime = newLesson.getEndTime();

                            if((l1StartTime.isAfter(l2StartTime) && l1StartTime.isBefore(l2EndTime))
                            || (l1EndTime.isAfter(l2StartTime) && l1EndTime.isBefore(l2EndTime))
                            || (currentLesson.getStartTime().equals(newLesson.getStartTime()))){
                                //clashed, return true
                                CourseUI cui = new CourseUI();
                                cui.printInfoMsg("FAILURE: \n",
                                        "Course: "+getCourseWithIndex(currentIndex.getIndexID()).getCourseCode()+"\n"+
                                                "Index: "+currentIndex.getIndexID()+"\n"+
                                                "Lesson: "+currentLesson.getLessonType()+"\n"+
                                                "Time slot: "+currentLesson.getStartTime()+" - "+currentLesson.getEndTime()+"\n"
                                                +"--- Clashes with ----\nCourse: "+getCourseWithIndex(newIndexID).getCourseCode()+"\n"
                                                +"Index: "+newIndex.getIndexID()+"\n"
                                                +"Lesson: "+newLesson.getLessonType()+"\n"
                                                +"Time slot: "+newLesson.getStartTime()+" - "+newLesson.getEndTime()+"\n");
                                cui.printErrorMessage(em.INDEX_HAS_CLASH_IN_TIMETABLE);

                                return true;
                            }
                        }
                    }
                }
            }
        }

        //will not clash
        return false;
    }

    /**
     * Check if two index can be swopped, if the new index will clash with user's current registered course
     * After check, print appropriate error message if clashed
     * The old index will be ignored as it need not to be considered
     * @param newIndexID IndexID of new index
     * @param oldIndexID IndexID of old index
     * @param matricNumber matric number of student
     * @see Course
     * @see Index
     * @see Lesson
     * @see CourseUI
     * @see ErrorMessage
     * @return true if there is clash, false if there are no clashes
     */
    public boolean checkIfTwoIndexClash(String newIndexID, String oldIndexID, String matricNumber){
        ErrorMessage em = new ErrorMessage();
        HashMap<Course, Index> registeredCourseAndIndex = getRegisteredCourseAndIndex(matricNumber);
        Index newIndex = getIndexFromID(newIndexID);

        Course currentCourse = getCourseWithIndex(oldIndexID);

        //Check for each registered course, each Index and each lesson, that time slot does not clash
        for(Course c : registeredCourseAndIndex.keySet()){
            //Do not need to check current with new index
            if(!currentCourse.getCourseCode().equals(c.getCourseCode())){
                for(Index currentIndex : c.getIndexes()){
                    //System.out.println("Checking if this index"+);
                    for(Lesson currentLesson : currentIndex.getLessonList()){
                        for(Lesson newLesson : newIndex.getLessonList()){
                            //If index time falls on the same day
                            if(currentLesson.getDayOfWeek().equals(newLesson.getDayOfWeek())){
                                // l2 cannot be within l1 timing
                                //if l1 start time ---------- l2 start time-------------l1 end time
                                //or if l1 start time ------------l2 end time -------------l1 end time
                                //or l1 start time
                                //   l2 start time
                                
                                LocalTime l1StartTime = currentLesson.getStartTime();
                                LocalTime l1EndTime = currentLesson.getEndTime();
                                LocalTime l2StartTime = newLesson.getStartTime();
                                LocalTime l2EndTime = newLesson.getEndTime();

                                if((l1StartTime.isAfter(l2StartTime) && l1StartTime.isBefore(l2EndTime))
                                || (l1EndTime.isAfter(l2StartTime) && l1EndTime.isBefore(l2EndTime))
                                || (currentLesson.getStartTime().equals(newLesson.getStartTime()))){
                                    //clashed, return true
                                    CourseUI cui = new CourseUI();
                                    cui.printInfoMsg("FAILURE: \n",
                                            "Course: "+getCourseWithIndex(currentIndex.getIndexID()).getCourseCode()+"\n"+
                                            "Index: "+currentIndex.getIndexID()+"\n"+
                                            "Lesson: "+currentLesson.getLessonType()+"\n"+
                                            "Time slot: "+currentLesson.getStartTime()+" - "+currentLesson.getEndTime()+"\n"
                                            +"--- Clashes with ----\nCourse: "+getCourseWithIndex(newIndexID).getCourseCode()+"\n"
                                            +"Index: "+newIndex.getIndexID()+"\n"
                                            +"Lesson: "+newLesson.getLessonType()+"\n"
                                            +"Time slot: "+newLesson.getStartTime()+" - "+newLesson.getEndTime()+"\n");
                                    cui.printErrorMessage(em.INDEX_HAS_CLASH_IN_TIMETABLE);

                                    return true;
                                }
                            }
                        }
                    }
                }
            }

        }

        //will not clash
        return false;
    }

    /**
     * Retrieves Index object from index ID specified.
     * @param indexID indexID of index
     * @see Index
     * @return Index Object
     */
    public Index getIndexFromID(String indexID){
        ArrayList<Course> allCourseList = getAllCourses();

        for(Course c : allCourseList) {
            for (Index ind : c.getIndexes()) {
                if (ind.getIndexID().equals(indexID)) {
                    return ind;
                }
            }
        }
        Index emptyIndex = new Index();
        return emptyIndex;
    }

    /**
     * Retrieves all Indexes from selected courses
     * @param courseCodeList list of course codes of selected courses
     * @return list of indexes of selected courses
     */
    public ArrayList<Index> getIndexesFromCourse(String[] courseCodeList){
        ArrayList<Index> listOfAllIndexes = getAllIndexes();
        ArrayList<Index> listOfIndexes = new ArrayList<Index>();
        for(int i = 0; i < courseCodeList.length; i++){
            for(Index ind : listOfAllIndexes){
                Course indexCourse = getCourseWithIndex(ind.getIndexID());
                if(indexCourse.getCourseCode().equals(courseCodeList[i])){
                    listOfIndexes.add(ind);
                }
            }
        }
        return listOfIndexes;
    }

    /**
     * Switches the student to be registered from current index to new index
     * @param currentIndexID Index ID of current index
     * @param newIndexID Index ID of new index
     * @param matricNumber matric number os student switching indexes
     * @see Course
     * @see Index
     * @return true if the switch was a success, false if the switch was not successful
     */
    public boolean switchIndex(String currentIndexID, String newIndexID, String matricNumber){
        FileManager fm = new FileManager();
        CourseUI cui = new CourseUI();
        GeneralMessage im = new GeneralMessage();
        boolean isRemoved = false;
        boolean isRegistered = false;
        ArrayList<Course> listOfAllCourse = fm.readCourseData();

        HashMap<Course, Index> listOfRegisteredCourse = getRegisteredCourseAndIndex(matricNumber);
        for(Course course : listOfAllCourse) {
            for(Course regCourse : listOfRegisteredCourse.keySet()){
                if (course.getCourseCode().equals(regCourse.getCourseCode())){
                    for (Index ind : course.getIndexes()) {
                        if (ind.getIndexID().equals(currentIndexID)) {
                            //Deregister student from current index
                            ind.getStudentsRegistered().remove(matricNumber);
                            //Register another student from Index waitlist
                            ind = deQueueFromWaitList(ind).copyObject();
                            isRemoved = true;
                        }
                        if (ind.getIndexID().equals(newIndexID)) {
                            //Add new student to course
                            ind.getStudentsRegistered().add(matricNumber);
                            isRegistered = true;
                        }
                    }
                }
            }
        }
        if(isRemoved && isRegistered){
            //Save changes to data
            fm.writeCourseData(listOfAllCourse);
            cui.printInfoMsg(im.SUCCESS,"The course has been switched.");
            return true;
        }
        else{
            cui.printInfoMsg(im.FAILURE,"The course has not been switched.");
            return false;
        }
    }

    /**
     * Checks if course exists in database
     * @param courseCode Course code of course to check
     * @return true if course can be found in database, false if course not found in database
     */
    public boolean checkCourseExist(String courseCode){
        //check if any course exist with same course code or course name
        ArrayList<Course> listOfAllCourses = getAllCourses();
        for(Course c : listOfAllCourses){
            if(c.getCourseCode().equals(courseCode)){
                ErrorMessage em = new ErrorMessage();
                CourseUI cui = new CourseUI();
                //cui.printErrorMessage(em.COURSE_ALREADY_EXISTS);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if index ID exsist in course specified using the course code
     * @param newIndexID Index ID of idnex
     * @param courseCode course code of course
     * @return true if index ID exists in course, and false if index ID does not exist in course
     */
    public boolean checkIndexIDExist(String newIndexID, String courseCode){
        Course c = getCourseWithCourseCode(courseCode);
        for (Index ind : c.getIndexes()) {
            if (ind.getIndexID().equals(newIndexID)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds student into course registered list and print the appropriate message if add was successful/unsuccessful
     * 
     * @param matricNumber matric number of student
     * @param selectedInd Index ID of index that student will be added in
     * @return true if student was added successfully, false if student was not added successfully
     */
    public boolean addStudentToCourse(String matricNumber, String selectedInd){
        CourseUI cui = new CourseUI();
        FileManager fm = new FileManager();
        GeneralMessage im = new GeneralMessage();
        boolean added =false;
        ArrayList<Course> listOfAllCourses = getAllCourses();
        for (Course c : listOfAllCourses) {
            for (Index ind : c.getIndexes()) {
                //Add student matric number into registered list
                if (ind.getIndexID().equals(selectedInd)) {
                    ArrayList<String> studentList = ind.getStudentsRegistered();
                    studentList.add(matricNumber);
                    ind.setStudentsRegistered(studentList);
                    //Save into indexes array
                    for(Index index : c.getIndexes()){
                        if(ind.getIndexID().equals(selectedInd)){
                            int indexPosition = c.getIndexes().indexOf(index);
                            c.getIndexes().set(indexPosition, ind);
                            break;
                        }
                    }
                    //Save into course array
                    for(Course course : listOfAllCourses){
                        if(course.getCourseCode().equals(c.getCourseCode())){
                            int coursePosition = listOfAllCourses.indexOf(c);
                            listOfAllCourses.set(coursePosition, c);
                            break;
                        }
                    }
                    added = true;
                    break;
                }
            }
        }
        if(added){
            //SAVE TO DATABASE
            fm.writeCourseData(listOfAllCourses);
            String successMsg = "The student has been successfully added to course.";
            cui.printInfoMsg(im.SUCCESS, successMsg);
        }
        else{
            //confirmation
            String failMsg = "FAIL: The student has not been added to course";
            cui.printInfoMsg(im.FAILURE, failMsg);
        }
        return added;

    }

    /**
     * Add student into course wait list and print the appropriate message if add was successful/unsuccessful.
     * Sends email to student after successful add
     * @param matricNumber matric number of student
     * @param selectedInd Index ID of index that student will be added in
     * @see GeneralMessage
     * @return true if student was added into wait list, false if student was not added into wait list
     */
    public boolean addStudentToWaitingList(String matricNumber, String selectedInd){
        CourseUI cui = new CourseUI();
        FileManager fm = new FileManager();
        GeneralMessage gm = new GeneralMessage();

        ArrayList<Course> listOfAllCourses = getAllCourses();
        for (Course c : listOfAllCourses) {
            for (Index ind : c.getIndexes()) {
                //Add student matric number into registered list
                if (ind.getIndexID().equals(selectedInd)) {
                    ind.getStudentsInWaitLists().add(matricNumber);
                    fm.writeCourseData(listOfAllCourses);
                    String successMsg = "The student has been successfully added to wait list.";
                    cui.printInfoMsg(gm.SUCCESS, successMsg);
                    
                    //Send email notification to use added to waitlist
                    NotificationAPI ne = new NotificationEmail();
                    StudentManager sm = new StudentManager();
                    Student se = sm.getStudentFromMatric(matricNumber);
                    ne.sendAddWaitList(se, c, selectedInd);
                    
                    return true;
                }
            }
        }
        //confirmation
        //System.out.println(listOfAllCourses.toString());
        String failMsg = "FAIL: The student has not been added to wait list";
        cui.printInfoMsg(gm.FAILURE, failMsg);
        return false;
    }

    /**
     * Checks if index ID exist in database
     * @param indexID Index ID of index to be checked
     * @return true if index exist in database, false if index does not exist in database
     */
    public boolean checkIfIndexExist(String indexID) {
        ArrayList<Course> listOfAllCourses = getAllCourses();
        for (Course c : listOfAllCourses) {
            for (Index ind : c.getIndexes()) {
                if (ind.getIndexID().equals(indexID)) {
                    return true;
                }
            }
        }
        CourseUI cu = new CourseUI();
        ErrorMessage em = new ErrorMessage();
        cu.printErrorMessage(em.INDEX_DOES_NOT_EXIST);
        return false;
    }

    /**
     * Updates the course with the new index
     * @param c Course object to be updated
     * @param newIndex updated Index object
     * @see Course
     * @see Index
     * @return Course objected that has contains Index object
     */
    public Course updateIndex(Course c, Index newIndex){
        Course updatedCourse = new Course();
        for(Index ind : c.getIndexes()){
            if(ind.getIndexID().equals(newIndex.getIndexID())){
                c.getIndexes().add(newIndex);
                c.getIndexes().remove(ind);
            }
        }
        updatedCourse = c.copyObject();
        return updatedCourse;
    }

    
     /**
      * Checks if two indexes are from the same course
      * @param indexNumber1 Index ID of the first index to be checked
      * @param indexNumber2 Index ID of the second index to be checked
      * @return true if both index are from same course, false if both indexes belong to different course
      */
    public boolean isIndexFromSameCourse(String indexNumber1, String indexNumber2){
        CourseUI cui = new CourseUI();
        ErrorMessage em = new ErrorMessage();
       
        Course c1 = getCourseWithIndex(indexNumber1);
        Course c2 = getCourseWithIndex(indexNumber2);

        if(!c1.isValid() || !c2.isValid()){
            cui.printErrorMessage(em.COURSE_DOES_NOT_HAVE_INDEX);
            return false;
        }
        else{
            if(c1.getCourseName().equals(c2.getCourseName())){return true;}
            else{cui.printErrorMessage(em.INDEX_DOES_NOT_BELONG_TO_SAME_COURSE);return false;}
        }
    }

    /**
     * Retrieves the wait list position of student of Index specified in Index ID
     * @param matricNumber matric number of student
     * @param indexID Index ID of index
     * @return the number of position the user is at in the waiting list
     */
    public int getWaitListPosition(String matricNumber, String indexID){
        Index selectedIndex = getIndexFromID(indexID);
        int position = 0;
        ArrayList<String> listOfWaitList = selectedIndex.getStudentsInWaitLists();
        for(int i = 0; i < listOfWaitList.size(); i++){
            if(listOfWaitList.get(i).equals(matricNumber)){
                //From 1 ... n
                position = i+1;
                break;
            }
        }
        return position;
    }
    /**
     * Notifies user of the swop success, through email using NotificationAPI
     * @param currentStudentMatric Matric number of current student
     * @param peerMatric Matric number of peer student
     * @param currentStudentCourse Course object to be swopped of current student
     * @param peerCourse Course object to be swopped of peer student
     * @param currentIndex Index ID of index belonging to current student
     * @param peerIndex Index ID of index belonging to peer student
     * @see NotificationAPI
     * @return true if the email was sent successfully, false if the email was not sent
     */
    public boolean notifySwopSuccess(String currentStudentMatric, String peerMatric, 
    Course currentStudentCourse, Course peerCourse,
    String currentIndex, String peerIndex){
        NotificationAPI ne = new NotificationEmail();
        Course[] courseArr = {currentStudentCourse, peerCourse};
        String[] indexArr = {currentIndex, peerIndex};
        String[] sMetric={currentStudentMatric,peerMatric};
        return ne.sendSwapSuccess(sMetric, courseArr, indexArr);
    }
}
