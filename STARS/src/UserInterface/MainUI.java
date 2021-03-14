package UserInterface;
import Entities.ErrorMessage;

import java.util.Scanner;
/**
 *MainUI handles the Interaction between System and External user
 *Mainly consist of input validation methods and basic display design methods
 */
abstract public class MainUI {
    ErrorMessage em = new ErrorMessage();
    /*
     *Abstract user interface methods 
     * @return
     */
    /**
     * Displays the main header and Retrieves user input to run respective methods
     * @return user choice 
     */
    public abstract int mainPage();
    /**
     * Retrieves a list of options available for user
     * @return List of user options
     */
    public abstract String[] getUserOptions();
    /**
     * Displays header for user
     */
    public abstract void getPageHeader();
   /**
     * Print a message from the ErrorMessage enum class
     * @see ErrorMessage
     * @param message ErrorMessage
     */
    public void printErrorMessage(String message){
        System.out.println(message);
    }
    /**
     * Prints a page break, used to split up different stages
     */
    public void printPageBreak(){
        System.out.println("|-----------------------------------------------------------------------------------------------------------|");
    };
    /**
     * Prints a shot divider to split up smaller sections
     */
    public void printDivider(){
        System.out.println("------------------------------------------------");
    }
    /**
     * Append spaces into a line to fit the main header in UI
     * @param line Message to be put into main header
     * @return line appended with design requirements
     */
    public static String addSpace(String line){
        int spaceMax = 107;
        for(int i = line.length(); i < spaceMax; i++){
            line = line + " ";
        }
        return line;
    }
    /**
     * Checks if input is integer and prints the appropriate error message
     * @param scannerInput user input
     * @return true if input is integer, false if input is not integer
     */
    public boolean checkInputInt(String scannerInput){
        ErrorMessage em = new ErrorMessage();
        try{
            Integer i1 = Integer.parseInt(scannerInput);
        }
        catch(NumberFormatException e){
            printErrorMessage(em.USER_INPUT_INVALID_TYPE);
            return false;
        }
        return true;
    }
    /**
     * Checks if input is integer and within list of options
     * @param scannerInput user input
     * @param optionSize max int use can choose from
     * @return true if interger input is integer and within the list of options, false otherwise
     */
    public boolean checkInputIntAndRange(String scannerInput, int optionSize){
        ErrorMessage em = new ErrorMessage();
        try{
            Integer i1 = Integer.parseInt(scannerInput);
            if(i1 > optionSize || i1 < 0){
                printErrorMessage(em.USER_INPUT_OUT_OF_RANGE);
                return false;
            }
        }
        catch(NumberFormatException e){
            printErrorMessage(em.USER_INPUT_INVALID_TYPE);
            return false;
        }
        return true;
    }
    /**
     * Checks for user confirmation to proceed
     * @param methodName Option for user to choose
     * @return true if user selected 1, false if user selected 0
     */
    public boolean getUserConfirmation(String methodName){
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do{
            System.out.println("Select your option: ");
            System.out.println("1. "+methodName);
            System.out.println("0. Go back to main menu");
            userInput = sc.nextLine();
        }while(!checkInputIntAndRange(userInput, 1));

        if(Integer.parseInt(userInput)==1)return true;
        return false;
    }

    /**
     * Checks for user confirmation to proceed
     * @param methodName Option for user to choose
     * @param additionalMessage Additonal message displayed
     * @return true if user selected 1, false if user selected 0
     */
    public boolean getUserConfirmationWithMsg(String methodName, String additionalMessage){
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println(additionalMessage);
        do{
            System.out.println("Select your option: ");
            System.out.println("1. "+methodName);
            System.out.println("0. Go back to main menu");
            userInput = sc.nextLine();
        }while(!checkInputIntAndRange(userInput, 1));

        if(Integer.parseInt(userInput)==1)return true;
        return false;
    }
    /**
     * Displays the list of options specified and get user input choice
     * @param options List of options
     * @return integer user choice out of all options
     */
    public int getUserMultipleOptions(String[] options){
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Please select your option: ");
        do{
            for(int i = 0; i < options.length; i++){
                System.out.println(i+1+". "+options[i]);
            }
            userInput = sc.nextLine();
        }while(!checkInputIntAndRange(userInput, options.length));

        return Integer.parseInt(userInput);
    }
    /**
     * Displays a question and gets integer input from user
     * @param question Question to be asked
     * @return integer input from user
     */
    public int getIntInput(String question){
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do{
            System.out.println(question);
            userInput = sc.nextLine();
        }while(!checkInputInt(userInput));

        return Integer.parseInt(userInput);
    }
    /**
     * Displays a question and gets integer input from user within a certain range
     * @param question Question to be asked
     * @param min min input accepted for question
     * @param max max input accepted for question
     * @return user integer input
     */
    public int getIntInputWithinRange(String question, int min, int max){
        Scanner sc = new Scanner(System.in);
        boolean typeValid = false;
        Integer userInput = null;
        do{
            try{
                do{
                    System.out.println(question);
                    userInput = Integer.parseInt(sc.nextLine());
                }while(!intIsWithinRange(userInput, min, max));
                typeValid = true;
            }
            catch(NumberFormatException e){
                printErrorMessage(em.USER_INPUT_INVALID_TYPE);
            }
        }while(!typeValid);

        return userInput;
    }
    /**
     * Checks if a number is within range
     * @param num Number to be checked
     * @param min Minimum value accepted for number
     * @param max Maximum value accepted for number
     * @return true if within min and max values, and false if not within min and max values
     */
    public boolean intIsWithinRange(int num, int min, int max){
        if(num < min || num > max){
            printErrorMessage(em.USER_INPUT_OUT_OF_RANGE);
            return false;
        }
        return true;
    }
    /**
     * Checks if string is within a certain number of characters
     * @param scannerInput User input
     * @param min Minimum characters required for the input
     * @param max Maximum characters accepted for input
     * @return true if number of characters is between min and max and false if number of characters is not between min and max
     */
    public boolean strIsWithinRange(String scannerInput, int min, int max){
        if((scannerInput.length()>max || scannerInput.length() < min)) {
            printErrorMessage(em.USER_INPUT_OUT_OF_RANGE);
            return false;
        }
        return true;
    }
    /**
     * Displays a question and gets student string input within a certain range of characters
     * @param message Questions to be asked
     * @param min Minimum number of characters the input can have
     * @param max Maximum number of characters the input can have
     * @return String input that is within the min and max characters
     */
    
    public String getStrInputWithinRange(String message, int min, int max){
        Scanner sc = new Scanner(System.in);
        String userInput;
        do{
            System.out.println(message);
            userInput = sc.nextLine();
        }while(!strIsWithinRange(userInput,min,max));
        return userInput;

    }
    /**
     * Print a message from the GeneralMessage enum class
     * @param message General message
     */
    public void printGeneralMessage(String message){
        System.out.println(message);
    }
}
