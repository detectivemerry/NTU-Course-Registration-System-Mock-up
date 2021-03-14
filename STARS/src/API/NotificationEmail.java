package API;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entities.Course;
import Entities.Student;
import Managers.StudentManager;

/** 
*Email implementation of Notification API, Enter the username/password for gmail inside string variable for Notification email
*
*@see NotificationAPI
*/
public class NotificationEmail implements NotificationAPI {

   final private String username = ""; // to be added
   final private String password = ""; // to be added
 
   public Boolean sendRegSuccess(Student se,ArrayList<Course> cse,ArrayList<String> indexID){
       String sbj="COURSE(S) ALLOCATED";
       String cnt ="";
               cnt+=loadStudent(se);
               cnt+= ("We are pleased to inform you that you have been allocated the following course(s) in" + "\n");
               cnt+= loadCourse(cse,indexID);
       boolean sendStatus=sendMessage(se.getEmail(), sbj,cnt);

       return sendStatus;
   }
/** 
*Email implementation of Notification API
*
*@see NotificationAPI
*/
   public Boolean sendAddWaitList(Student se, Course cse,String indexID ){

    String sbj="Allocated to WaitList";
       String cnt ="";
               cnt+=loadStudent(se);
               cnt+= ("The following courses have been added in waiting list" + "\n");
               ArrayList<Course> csea=new ArrayList<Course>(Arrays.asList(cse));
               ArrayList<String> inda=new ArrayList<String>(Arrays.asList(indexID));
               cnt+=loadCourse(csea,inda);
               boolean sendStatus=sendMessage(se.getEmail(), sbj, cnt);
    return sendStatus;
   }
/** 
*Email implementation of Notification API
*
*@see NotificationAPI
*/
   public Boolean sendSwapSuccess(String[] sMetric,Course[] ci,String[] indSwap){
       //Send email to both user and peer DONE in course manager
        //Pass in 2 matric number, pass in courses array, and 2 IndexID
       String sbj="SWAP SUCCESS";
       StudentManager sm=new StudentManager();
        Student st1= sm.getStudentFromMatric(sMetric[0]);
        Student st2=sm.getStudentFromMatric(sMetric[1]);
        Student[] st={st1,st2};
        String msg=indexSwapString( st, ci, indSwap, true);
        String msg2=indexSwapString( st, ci, indSwap, false);

        Boolean sStatus1=sendMessage(st1.getEmail(), sbj, msg);
        Boolean sStatus2=sendMessage(st2.getEmail(), sbj, msg2);

        if(sStatus1&&sStatus2){
            return true;
        }
        else{
            return false;
        }
        
   }

/** 
*Send email
*
*@param emailDest Destination email
*@param emSub Subject of Email
*@param emCont String Content of email
*@return Success/Failure of sending email
*/
private Boolean sendMessage(String emailDest,String emSub, String emCont) {

    // need to include java activation framework
    //HARDCODED//////////////////////////////////////////////////////////////
     //emailDest = "c190106@e.ntu.edu.sg";

    Boolean sendStatus=false;
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("tempcronjam@gmail.com"));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDest)); // to be
                                                                                                         // added an
                                                                                                         // email
                                                                                                         // addr

        message.setSubject(emSub);
        message.setText(emCont);

        Transport.send(message);

        System.out.println("Email has been sent.");
        sendStatus=true;
        return sendStatus;

    } catch (MessagingException e) {
        //throw new RuntimeException(e);
        return sendStatus;
    }
}


/** 
*Convert Information required for email into String
*@param st Student Array of Swapped Students
*@param ci Course Array of Course being swapped
*@param indSwap String Array of Index of course being swapped
*@param swapper <code>true</code> if the return string is intended for swapper <code>false</code> if swapee
*@return String for email to be sent to swapper/swappee
*/
   private String indexSwapString(Student[] st, Course[] ci, String []indSwap, Boolean swapper){
    String s1;
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MMM-yy");
    LocalDate ld= LocalDate.now();
    String d=ld.format(dtf);
       if(swapper){
        s1=loadStudent(st[0]);
        s1+=("\n Swap Reqested : \n"+"--------------------------"+"\n");
        s1+=loadStudent(st[1]);
        s1+=("Course\tTitle \t \t \t New Index \t Course Type \t Date Allocated \n"
        +" -----------------------------------------------------------------------------------------------------------------\n");
        s1+=("" + ci[0].getCourseCode() + "\t" + ci[0].getCourseName()+ "\t" + indSwap[1]+"\t"+ci[0].getCourseType()+" \t "+d+"\n") ;
        s1+=("Course\tTitle \t \t \t Old Index \t Course Type \t Date Allocated \n"
        +" -----------------------------------------------------------------------------------------------------------------\n");
        s1+=("" + ci[0].getCourseCode() + "\t" + ci[0].getCourseName()+ "\t" + indSwap[0]+"\t"+ci[0].getCourseType()+" \t "+d+"\n") ;
       }
    else{
        s1=loadStudent(st[1]);
        s1+=("\n Swap Reqested : \n"+"--------------------------"+"\n");
        s1+=loadStudent(st[0]);
        s1+=("Course\tTitle \t \t \t New Index \t Course Type \t Date Allocated \n"
        +" -----------------------------------------------------------------------------------------------------------------\n");
        s1+=("" + ci[0].getCourseCode() + "\t" + ci[0].getCourseName()+ "\t" + indSwap[0]+"\t"+ci[0].getCourseType()+" \t "+d+"\n") ;
        s1+=("Course\tTitle \t \t \t Old Index \t Course Type \t Date Allocated \n"
        +" -----------------------------------------------------------------------------------------------------------------\n");
        s1+=("" + ci[0].getCourseCode() + "\t" + ci[0].getCourseName()+ "\t" + indSwap[1]+"\t"+ci[0].getCourseType()+" \t "+d+"\n") ;

        

    }
    return s1;


    

     
   }
/** 
*Convert courses and indexes into String
*@param cse Courses that were registered for
*@param indexID IndexID for each of the courses that that were registered
*@return String of Courses that have been registered
*/
   private String loadCourse(ArrayList<Course> cse,ArrayList<String> indexID) {

    String cnt="";
    String title = ("Course\tTitle \t \t \t Index \t Course Type \t Date Allocated \n"
            + " -----------------------------------------------------------------------------------------------------------------\n");
    cnt+=title;


    for (int i=0;i<cse.size();i++) {

       // Index tmpInd = c.getIndexes().get(0);
       // CourseType courseT=tmpInd.getStudWaitList().get(0).getRegCt();

        //Include current date as allocation date
        LocalDate ld=LocalDate.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MMM-yy");
        String d=ld.format(dtf);
        String toadd = "" + cse.get(i).getCourseCode() + "\t" + cse.get(i).getCourseName()+ "\t" + indexID.get(i)+"\t"+cse.get(i).getCourseType()+" \t "+d+"\n" ;
        cnt+=toadd;
    }

    return cnt;
}



/** 
*Convert student specific information into string
*
*@param se Student object
*@return Formatted String for Student
*/

   private String loadStudent(Student se){
       String cnt = ("Name \t:" + se.getName() + "\n" + "Matric No \t:" + se.getMatricNumber() + "\n" + "Programme \t:"
               + se.getProgram() + "\n" + "Year of Study \t:" + se.getStudyYear() + "\n");
               return cnt;
   }
}
