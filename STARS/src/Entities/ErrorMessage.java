package Entities;
/**
 * All the error messages that will prompt for validation of input by users
 */
public class ErrorMessage {

    //General


    // Login
    public final String INVALID_ACCOUNT_TYPE = "ERROR: Invalid account type!";
    public final String INVALID_USERNAME_PASSWORD_COMBINATION = "ERROR: Wrong combination of username and password.";

    //Entities.Student
    public final String USER_INPUT_OUT_OF_RANGE = "ERROR: The number you have entered is not within given range. Please try again.";
    public final String USER_INPUT_INVALID_TYPE = "ERROR: The input type you have entered is invalid. Please try again.";
    public final String USER_DOES_NOT_HAVE_INDEX = "ERROR: The index you have entered is not registered to the account.";
    public final String INDEX_DOES_NOT_BELONG_TO_SAME_COURSE = "ERROR: The index you have entered does not belong to same course.";
    public final String INDEX_HAS_NO_VACANCY = "ERROR: The index you have entered has no vacancy.";
    public final String INDEX_HAS_CLASH_IN_TIMETABLE = "ERROR: The index you have entered is overlapping your indexes time slots.";
    public final String COURSE_DOES_NOT_HAVE_INDEX = "ERROR: The index you have entered is not registered in selected course.";
    public final String USER_ALREADY_REGISTERED_IN_COURSE = "ERROR: Student is already registered in specified course.";
    public final String INDEX_DOES_NOT_EXIST = "ERROR: Index does not exist.";
    public final String USER_ALREADY_EXISTS = "ERROR: This user already exists.";
    public final String COURSE_DOES_NOT_EXIST = "ERROR: Course does not exist.";
    public final String EMAIL_UNABLE_TO_SEND = "ERROR: Unable to send email, please ensure email is valid.";
    //public final String ALREADY_REGISTERED_IN_COURSE = "ERROR: Already registered in course under a different index";

    //SemManager
    public final String START_DATE_AFTER_END_DATE = "ERROR: Start date cannot be before the end date.";
    public final String INVALID_DATE_OR_TIME = "ERROR: Please enter date or time values within range!";
    public final String NOT_WITHIN_ACCESS_PERIOD ="ERROR: Please access the system only during access period.";
    // Entities.Staff
    public final String INVALID_SCHOOL = "ERROR: Invalid school! Please try again.";
    public final String COURSE_ALREADY_EXISTS = "ERROR: This course already exists.";

}