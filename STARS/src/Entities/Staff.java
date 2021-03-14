package Entities;

import Enums.Gender;
import Enums.Nationality;

import java.io.Serializable;
/**
 * Represents the staff user
 * Extends User and implements Serializable
 */
public class Staff extends User implements Serializable {
    private String staffID;

    public Staff(){
    }

    public Staff(String usrName,char[] pwrd){
super(usrName, pwrd);

    }
    public Staff(String usrName,char[] pwrd,String email){
        super(usrName, pwrd,email);
    }

    public Staff(String usrName, char[] pwd, String name, Nationality nationality, Gender gender, String staffID){
        super(usrName, pwd,name,nationality,gender);
        this.staffID=staffID;
    }
    public Staff(String usrName,char[] pwd,String name,Nationality nationality, Gender gender,String staffID,String email){
        super(usrName, pwd,name,nationality,gender,email);
        this.staffID=staffID;
    }
    
}
