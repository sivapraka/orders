package com.demo.orders.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by admin on 24-Mar-18.
 */

public class UserDataSource implements Serializable {

    private String userName;
    private String userID;
    private String userImage;
    private transient Bitmap userBitmapImage;
    private byte[] userByteImage;

    public UserDataSource(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;
    }

    public UserDataSource(String userName, String userID, String userImage) {
        this.userName = userName;
        this.userID = userID;
        this.userImage = userImage;
    }

    //Bitmap Image
    public UserDataSource(String userName, String userID, Bitmap userBitmapImage) {
        this.userName = userName;
        this.userID = userID;
        this.userBitmapImage = userBitmapImage;
    }

    // Byte[] image
    public UserDataSource(String userName, String userID, byte[] userBitmapImage) {
        this.userName = userName;
        this.userID = userID;
        this.userByteImage = userBitmapImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Bitmap getUserBitmapImage() {
        return userBitmapImage;
    }

    public void setUserBitmapImage(Bitmap userBitmapImage) {
        this.userBitmapImage = userBitmapImage;
    }
}
