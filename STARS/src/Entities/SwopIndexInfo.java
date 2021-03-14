package Entities;
/**
 * A class to contain swop index information 
 */
public class SwopIndexInfo {

    private String peerUsername;
    private char[] peerPassword;
    private String currentStudentIndex;
    private String peerIndex;
    /**
     * Creates a swop index information object
     * @param indexNumber1 The current student index number
     * @param peerUsername The peer student user name
     * @param peerPassword The peer student password
     * @param indexNumber2 The peer student index number
     */
    public SwopIndexInfo(String indexNumber1, String peerUsername, char[] peerPassword, String indexNumber2){
        this.currentStudentIndex = indexNumber1;
        this.peerUsername = peerUsername;
        this.peerPassword = peerPassword;
        this.peerIndex = indexNumber2;
    }

    /**
     * Retrieves the peer username
     * @return username 
     */
    public String getPeerUsername() {
        return peerUsername;
    }
    /**
     * Retrieves the peer password
     * @return password
     */
    public char[] getPeerPassword() {
        return peerPassword;
    }
    /**
     * Retrieves the current student index
     * @return index ID
     */
    public String getCurrentStudentIndex() {
        return currentStudentIndex;
    }
    /**
     * Retrieves the peer index 
     * @return index ID
     */
    public String getPeerIndex() {
        return peerIndex;
    }

}
