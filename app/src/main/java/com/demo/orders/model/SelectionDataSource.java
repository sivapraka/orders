package com.demo.orders.model;

import java.io.Serializable;

public class SelectionDataSource implements Serializable {

    private int ID;
    private String Name;
    private String UserID;

    public SelectionDataSource(int ID, String userID, String Name) {
        this.UserID = userID;
        this.Name = Name;
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
