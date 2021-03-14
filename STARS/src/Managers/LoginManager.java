package Managers;
import UserInterface.*;
import Entities.*;
import java.time.LocalDateTime;
/**
 * Login manager aids in verifying user through login UI
 */
public class LoginManager {

/**
*Constructor for Login Manager
*
*/
  public LoginManager(){
  }

/**
*Begin Login Sequence for Student
*@return A User object with username and password
*@see User
*/
    public User getLogin(){
        LoginUI loginUi=new LoginUI();
        User loginInfo;
        loginInfo = loginUi.getLoginInfo();
        return loginInfo;
    }

/**
*Begin Login Sequence 
*
*/
    public void startLogin(){
        LoginUI loginUI = new LoginUI();
        SemRegistManager semManager = new SemRegistManager();
        //NotificationAPI ni=new NotificationEmail();
        //CourseManager cm=new CourseManager();
       // Course[] c={cm.getCourseWithCourseCode("CB3001"),cm.getCourseWithCourseCode("CB3001")};
        //String[] inx={"11023","11024"};
       // ni.sendSwapSuccess("U1921884J", "U1345233A", c ,inx );
        int choice = loginUI.mainPage();
        switch(choice){
            case 1://Student login
                User userStudent = new User();
                userStudent = getLogin();
                if(validateStudent(userStudent)){
                    if(!semManager.isWithinPeriod(LocalDateTime.now()))startLogin();
                    Student newStudent = new Student(userStudent.getUsername(), userStudent.getPassword());
                    StudentManager studentManager = new StudentManager(newStudent);
                    studentManager.run();
                }
                else{startLogin();}
                
                break;

            case 2://Staff Login
                User userStaff = new User();
                userStaff = getLogin();
                if(validateStaff(userStaff)){
                    Staff newStaff = new Staff(userStaff.getUsername(), userStaff.getPassword());
                    StaffManager staffManager = new StaffManager (newStaff);
                    staffManager.run();
                }
                else{
                    startLogin();
                }
                break;
        }
    }
/**
*Validate student with login information
*@return Is the login information valid for student
*@param userStaff the logininfo for student containing username and password
*/
    public boolean validateStudent(User userStaff){
        StudentManager studentManager = new StudentManager();
        boolean isValid = studentManager.validateStudent(userStaff);
        return isValid;
    }

/**
*Validate staff with login information
*@return Is the login information valid for staff
*@param userStaff the logininfo for staff containing username and password
*/
    public boolean validateStaff(User userStaff){
        StaffManager staffManager = new StaffManager();
        boolean isValid = staffManager.validateStaff(userStaff);
        return isValid;
    }

   
}
