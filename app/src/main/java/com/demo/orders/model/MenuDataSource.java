package com.demo.orders.model;

import java.io.Serializable;

public class MenuDataSource implements Serializable {

    private String title;
    private String screen_name;
    private int photo;
    private String image;
//    private  String languagecode;
//    private String menuname;


    public MenuDataSource(String title, String screen_name, String image) {
        this.title = title;
        this.screen_name = screen_name;
        this.image = image;
    }

    public MenuDataSource(String title, int photo) {
        this.title = title;
        this.photo = photo;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
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

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    //    public String getLanguagecode() {
//        return languagecode;
//    }
//
//    public void setLanguagecode(String languagecode) {
//        this.languagecode = languagecode;
//    }
//
//    public String getMenuname() {
//        return menuname;
//    }
//
//    public void setMenuname(String menuname) {
//        this.menuname = menuname;
//    }
}
