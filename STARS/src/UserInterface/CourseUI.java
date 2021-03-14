package UserInterface;
/**
 * CourseUI handles the Interaction between System and External user
 */
public class CourseUI{
    /**
     * Prints the success or failure state of operation
     * @param success_or_failure String to state whether the operation was successful or failure
     * @param additionalMsg String to state any additional message
     */
    public void printInfoMsg(String success_or_failure, String additionalMsg){
        System.out.println(success_or_failure+additionalMsg);
    }
    /**
     * Print a message from the GeneralMessage enum class
     * @param message General message to be printed
     */
    public void printGeneralMessage(String message){
        System.out.println(message);
    }
    /**
     * Print a message from the ErrorMessage enum class
     * @param string Error message to be printed
     */
    public void printErrorMessage(String string){
        System.out.println(string);
    }

}
