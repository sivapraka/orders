package com.demo.orders.model;

import java.io.Serializable;

public class NavDrawerItem implements Serializable {
    private boolean showNotify;
    private String title;
    private int photo;
    private String image;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public NavDrawerItem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public NavDrawerItem(String title, int photo) {
        this.title = title;
        this.photo = photo;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
