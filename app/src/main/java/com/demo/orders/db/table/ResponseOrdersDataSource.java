package com.demo.orders.db.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOrdersDataSource {

    @SerializedName("data")
    @Expose
    private List<OrdersDataSourceTable> data = null;

    public List<OrdersDataSourceTable> getData() {
        return data;
    }

    public void setData(List<OrdersDataSourceTable> data) {
        this.data = data;
    }

}
