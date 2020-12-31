package com.demo.orders.fragment;

import com.demo.orders.model.CustomersDataSource;


public interface CustomerListener {
    void onClickButton(int position, CustomersDataSource customersDataSourceList, CustomersDataSource customersAccountDataScource); // create callback function

}