import java.io.Console;
import Managers.FileManager;
import Managers.LoginManager;

/**
 * STARS
 * My STudent Automated Registration System (My STARS)
 * is a university application meant for each School's academic staff and undergraduate students.
 * 
 * @author SS6 GROUP 2
 * @version 1.0
 * @since 2020-11-24
 */
public class STARS {
    /**
     * This is the main method for the STARS program.
     * @param args stores the incoming command line argument for the program. Unused.
     */
   public static void main(String args[]) {
    FileManager fm = new FileManager();
    fm.preload();
    Console console = System.console();
    if(console==null){
        System.out.println("For login purpose, please run our application through a console.");
        System.out.println("An IDE that runs through command prompt (eg. Visual Studio Code) is also supported");
    }
    LoginManager lm = new LoginManager();
    lm.startLogin();
   }

}
