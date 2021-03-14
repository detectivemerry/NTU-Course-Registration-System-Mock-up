package Managers;
import Entities.Venue;
import java.util.ArrayList;
/**
 * VenueManager contains the logic to coordinate and realise use case
 */
public class VenueManager {
    /**
     * Retrieves all venues in database
     * @see FileManager
     * @see Venue
     * @return list of venues
     */
    public ArrayList<Venue> getAllVenue(){
        FileManager fm = new FileManager();
        ArrayList<Venue> venueList = fm.readVenueData();
        return venueList;
    }
}
