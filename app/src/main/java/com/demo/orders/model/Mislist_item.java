package com.demo.orders.model;

public class Mislist_item {

    int fimage;
    String name;

    public Mislist_item(int fimage, String name) {
        this.fimage = fimage;
        this.name = name;
    }

    public int getFimage() {
        return fimage;
    }

    public void setFimage(int fimage) {
        this.fimage = fimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
