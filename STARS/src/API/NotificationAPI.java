package API;

import java.util.ArrayList;

import Entities.Course;
import Entities.Student;
/**
*Interface for Notification of Students
*
*/
public interface NotificationAPI {

/** 
*Notify swapper and swappee of the swop success
*
*@param sMetric the metric of swapper reqestor [0] and swappee [1] 
*@param ci the Course of  swapper reqestor [0] and swappee [1]
*@param indSwap the index id of swapper reqestor [0] and swappee [1]
*@return Success/Failure of sending swap notification
*/
    public Boolean sendSwapSuccess(String[] sMetric ,Course[] ci,String[] indSwap);
/** 
*Notify student of success of adding courses
*
*@param se Student which requested the courses
*@param cse Courses which the student reqested for
*@param indexID the IndexIDs  of the Courses which the student reqested for, in the same sequence as cse
*@return Success/Failure of sending notification
*/
    public Boolean sendRegSuccess(Student se,ArrayList<Course> cse,ArrayList<String> indexID);

/** 
*Notify student of success of adding to waitlist
*
*@param se Student which requested the course
*@param cse Course which the student reqested for
*@param indexID the IndexID of the Course which the student reqested for
*@return Success/Failure of sending notification
*/
    public Boolean sendAddWaitList(Student se, Course cse,String indexID );
}
