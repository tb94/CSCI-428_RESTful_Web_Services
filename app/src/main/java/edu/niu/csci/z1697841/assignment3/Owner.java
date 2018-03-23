package edu.niu.csci.z1697841.assignment3;

import java.io.Serializable;

/**
 * Created by Tim on 3/20/2018.
 * <p>
 * Object to represent a pet owner
 * Holds value of owner id, first name, and last name
 * With appropriate getters
 */

public class Owner implements Serializable {
    private int oid;
    private String firstName;
    private String lastName;

    public Owner(String oid, String firstName, String lastName) {
        this.oid = Integer.parseInt(oid);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getOid() {
        return oid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
