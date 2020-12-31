package com.demo.orders.db.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOrders {

    @SerializedName("data")
    @Expose
    private List<ResponseOrderTable> data = null;

    public List<ResponseOrderTable> getData() {
        return data;
    }

    public void setData(List<ResponseOrderTable> data) {
        this.data = data;
    }

}
