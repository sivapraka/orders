package com.demo.orders.model;

import java.io.Serializable;

/**
 * Created by admin on 03-Apr-18.
 */

public class ColorDataSource implements Serializable {

    private String name;
    private int color;


    public ColorDataSource(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
