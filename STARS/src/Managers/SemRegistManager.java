package Managers;

import Entities.ErrorMessage;
import Entities.SemRegistPeriod;
import UserInterface.MainUI;
import UserInterface.StaffUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * SemRegistManager contains the logic to coordinate and realise use case
 */
public class SemRegistManager {
    /**
     * Retrieves the current access period from database
     * @return current access period
     */
    public SemRegistPeriod getCurrentAccessPeriod(){
        FileManager fm = new FileManager();
        SemRegistPeriod currentAccessPeriod = fm.readSemAccessData();
        return currentAccessPeriod;
    }
    /**
     * Retrieves the the current start of access period 
     * @return current start of access period 
     */
    public LocalDateTime getAccessPeriodStart(){
        LocalDateTime startPeriod = getCurrentAccessPeriod().getStartDateTime();
        return startPeriod;
    }
    /**
     * Retrieves the current end of access period
     * @return current end of access period 
     */
    public LocalDateTime getAccessPeriodEnd(){
        LocalDateTime endPeriod = getCurrentAccessPeriod().getEndDateTime();
        return endPeriod;
    }
    /**
     * Checks if access time is within access period
     * @param localDateTime The time to be checked 
     * @return Returns true if the time is within specified period, false if the time is not within specified period
     */
    public boolean isWithinPeriod(LocalDateTime localDateTime){
        LocalDateTime cmts =getCurrentAccessPeriod().getStartDateTime();
        LocalDateTime cmte =getCurrentAccessPeriod().getEndDateTime();
        if (localDateTime.isAfter(cmts) && localDateTime.isBefore(cmte)) {
            return true;
        }
        ErrorMessage em = new ErrorMessage();
        System.out.println(em.NOT_WITHIN_ACCESS_PERIOD);;
        return false;
    }
    /**
     * Checks if the End of access period is after the start of access period
     * @param accessPeriodList List of start and end access time to be checked
     * @return True if end of access period is after start of access period and false if end of access period is before start of access period
     */
    public boolean checkValidAccessPeriod(ArrayList<LocalDateTime> accessPeriodList){
        LocalDateTime startTime = accessPeriodList.get(0);
        LocalDateTime endTime = accessPeriodList.get(1);

        if(endTime.isAfter(startTime))return true;
        
        //Start date is after end date, return error
        ErrorMessage em = new ErrorMessage();
        StaffUI staffUI = new StaffUI();
        staffUI.printErrorMessage(em.START_DATE_AFTER_END_DATE);
        return false;
    }
    /**
     * Replaces the current access period with the new access period
     * @param accessPeriodList List of start and end date time to be added into database
     */
    public void addNewAccessPeriod(ArrayList<LocalDateTime> accessPeriodList){
        FileManager fm = new FileManager();
        SemRegistPeriod semRegistPeriod = getCurrentAccessPeriod();
        semRegistPeriod.setStartDate(accessPeriodList.get(0));
        semRegistPeriod.setEndDate(accessPeriodList.get(1));
        //Save to database
        fm.writeSemAccessData(semRegistPeriod);
    }
}
