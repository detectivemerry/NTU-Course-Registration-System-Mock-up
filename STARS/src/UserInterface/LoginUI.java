package UserInterface;
import Entities.User;
import Managers.LoginManager;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
/**
 * LoginUI retrieves login details from users
 */
public class LoginUI extends MainUI{

    @Override
    public int mainPage() {
        getPageHeader();
        //display all options for login
        System.out.println("List of account types:");
        String[] options = getUserOptions();
        for(int i = 0; i < options.length; i++){
            System.out.println(options[i]);
        }
        String choiceQst = "Select your account type";
        int choice = getIntInputWithinRange(choiceQst,1,options.length);
        return choice;
    }

    @Override
    public String[] getUserOptions() {
        String[] options = new String[]{
                "1. Student account",
                "2. Staff account"
        };
        return options;
    }

    @Override
    public void getPageHeader() {
        System.out.println("|-----------------------------------------------------------------------------------------------------------|\n" +
                "|                                                                                                           |\n" +
                "|"+addSpace("STARS")+"|\n"+
                "|"+addSpace("")+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|\n"+
                "|"+addSpace("Login Page")+"|\n"+
                "|-----------------------------------------------------------------------------------------------------------|");

    }

    public int getAccountType(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("| May I know who are you?\t\t\t |");
        System.out.println("|\tSelect: \n 1 for STUDENT \n 2 for STAFF");
        System.out.println("--------------------------------------------------");
        int accountChoice = Integer.parseInt(sc.nextLine());
        return accountChoice;
    }

    public User getLoginInfo() {
        //Needs to run via visual studio code

    	Console console = System.console();
        Scanner sc = new Scanner(System.in);

        String userNameMsg = "Enter username (Max 20 Characters):";
        String username = getStrInputWithinRange(userNameMsg,1,20);
        //String username=console.readLine("Enter console username");

        char[] password = console.readPassword("Enter your password: ");

////////////W/O CONSOLE////////////////////////////////////////////////////////////////////////////////////
//        char password[] = null;
//        try {
//            password = PasswordField.getPassword(System.in, "Enter your password: ");
//        } catch(IOException ioe) {
//            ioe.printStackTrace();
//        }
//        for(int i = 0; i < password.length; i++){
//            System.out.println(password[i]);
//        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

       // System.out.println(password);
        return new User(username, password);

    }


}
