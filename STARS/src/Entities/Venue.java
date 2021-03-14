package Entities;

import java.io.Serializable;

/**
 * Represents the venue of a lesson
 * Implements Serializable class
 */
public class Venue implements Serializable {
    String venueName;
    String blockName;
    String venueBlockFloorName;

    /**
     * Create a Venue object
     * @param venueName Name of venue
     * @param blockName Block of venue
     * @param venueBlockFloorName Combination of venue name and venue block
     */
    public Venue(String venueName, String blockName, String venueBlockFloorName){
        this.venueName = venueName;
        this.blockName = blockName;
        this.venueBlockFloorName = venueBlockFloorName;
    }

    /**
     * Retrieves the venue name of a venue
     * @return venue name of venue
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * Retrieves the block name of venue
     * @return block name of venue
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * Retrieves the venue block floor name
     * @return venue block floor name 
     */
    public String getVenueBlockFloorName() {
        return venueBlockFloorName;
    }

    /**
     * Modifies the venue name for this venue
     * @param venueName New Venue name for venue to be set in the venue object
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    /**
     * Modifies the block name 
     * @param blockName New block name for venue to be set in the venue object
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    /**
     * Modifies the venue block floor name
     * @param venueBlockFloorName New venue and block name for venue to be set in the venue object
     */
    public void setVenueBlockFloorName(String venueBlockFloorName) {
        this.venueBlockFloorName = venueBlockFloorName;
    }
}
