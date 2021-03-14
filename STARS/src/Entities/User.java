package Entities;
import Enums.*;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a user registered in STARS
 * A user can be a student or a staff
 * Implements Serializable class
 */
public class User implements Serializable {
    /**
     * Private instance attributes of user
     */
    private String username;
    private char[] password;
    private String name;
    private Nationality nationality;
    private Gender gender;
    private String email;

    /**
     * Default constructor
     */
    public User() {
	}

    public User(String username, char[] password){
        this.username = username;
        this.password = password;
    }
    public User(String username, char[] password,String email){
        this.username = username;
        this.password = password;
        this.email=email;
    }
    public User(String username,char[]password,String name,Nationality nationality, Gender gender){
        this.username=username;
        this.password=password;
        this.name=name;
        this.nationality=nationality;
        this.gender=gender;
    }
    public User(String username,char[]password,String name,Nationality nationality, Gender gender,String email){
        this.username=username;
        this.password=password;
        this.name=name;
        this.nationality=nationality;
        this.gender=gender;
        this.email=email;
    }

    /**
     * Retrieves the username of the user
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the user
     * @return password of user
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Retrieves the name of the user
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the user
     * @param name New name to be set into this User object
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retrieves the nationality of the user
     * @return nationality of user
     */
    public Nationality getNationality() {
        return nationality;
    }
    
    /**
     * Modifies the nationality of the user
     * @param nationality New nationality to be set into this User object
     */
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * Retrieves the gender of the user
     * @return Retrieves the user gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Modifies the gender of the user
     * @param gender New gender to be set into this User object
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the email of the user
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifies the email of the user
     * @param email New venue to be set into this user object
     */
    public void setEmail(String email) {
        this.email = email;
    }

}