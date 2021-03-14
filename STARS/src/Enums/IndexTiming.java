package Enums;


/**
 * Types of index timing a lesson could have
 */
public enum IndexTiming {
    EIGHT_THIRTY("830"),
    NINE_THIRTY("930"),
    TEN_THIRTY("1030"),
    ELEVEN_THIRTY("1130"),
    TWELVE_THIRTY("1230"),
    THIRTEEN_THIRTY("1330"),
    FOURTEEN_THIRTY("1430"),
    FIFTEEN_THIRTY("1530"),
    SIXTEEN_THIRTY("1630"),
    SEVENTEEN_THIRTY("1730"),
    EIGHTEEN_THIRTY("1830"),
    NINETEEN_THIRTY("1930"),
    TWENTY_THIRTY("2030"),
    TWENTYONE_THIRTY("2130"),
    TWENTYTWO_THIRTY("2230");

    public final String label;
    /**
     * Constructor for IndexTiming
     */
    private IndexTiming(String label){
        this.label = label;
    }

}
