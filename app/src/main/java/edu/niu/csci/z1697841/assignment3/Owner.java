package edu.niu.csci.z1697841.assignment3;

import java.io.Serializable;

/**
 * Created by Tim on 3/20/2018.
 */

public class Owner implements Serializable {
    private int oid;
    private String firstName;
    private String lastName;

    public Owner() {
    }

    public Owner(String oid, String firstName, String lastName) {
        this.oid = Integer.parseInt(oid);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
