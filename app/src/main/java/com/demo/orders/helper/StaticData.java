package com.demo.orders.helper;

import android.content.Context;

import com.demo.orders.R;
import com.demo.orders.model.ColorDataSource;
import com.demo.orders.util.BitmapUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15-Mar-18.
 */

public class StaticData {
    CommonClass commonClass;
    BitmapUtility bitmapUtility;
    List<StateDataSource> stateDataSourceList = null;
    private Context context;
    private String TAG = "Static Data";

    public StaticData(Context ctxt) {
        this.context = ctxt;
        commonClass = new CommonClass(ctxt);
        bitmapUtility = new BitmapUtility(ctxt);
    }

    //Color List for User Image Dynamic change
    public List<ColorDataSource> getColorList() {
        List<ColorDataSource> allItems = new ArrayList<ColorDataSource>();
        allItems.add(new ColorDataSource("A", R.color.colorA));
        allItems.add(new ColorDataSource("B", R.color.colorB));
        allItems.add(new ColorDataSource("C", R.color.colorC));
        allItems.add(new ColorDataSource("D", R.color.colorD));
        allItems.add(new ColorDataSource("E", R.color.colorE));
        allItems.add(new ColorDataSource("F", R.color.colorF));
        allItems.add(new ColorDataSource("G", R.color.colorG));
        allItems.add(new ColorDataSource("H", R.color.colorH));
        allItems.add(new ColorDataSource("I", R.color.colorI));
        allItems.add(new ColorDataSource("J", R.color.colorJ));
        allItems.add(new ColorDataSource("K", R.color.colorK));
        allItems.add(new ColorDataSource("L", R.color.colorL));
        allItems.add(new ColorDataSource("M", R.color.colorM));
        allItems.add(new ColorDataSource("N", R.color.colorN));
        allItems.add(new ColorDataSource("O", R.color.colorO));
        allItems.add(new ColorDataSource("P", R.color.colorP));
        allItems.add(new ColorDataSource("Q", R.color.colorQ));
        allItems.add(new ColorDataSource("R", R.color.colorR));
        allItems.add(new ColorDataSource("S", R.color.colorS));
        allItems.add(new ColorDataSource("T", R.color.colorT));
        allItems.add(new ColorDataSource("U", R.color.colorU));
        allItems.add(new ColorDataSource("V", R.color.colorV));
        allItems.add(new ColorDataSource("W", R.color.colorW));
        allItems.add(new ColorDataSource("X", R.color.colorX));
        allItems.add(new ColorDataSource("Y", R.color.colorY));
        allItems.add(new ColorDataSource("Z", R.color.colorZ));
        return allItems;
    }

    //get the Input (User/Customer/Product/CompanyName) and Return the name and color for the respective input fields
    public List<ColorDataSource> getSelectedName(String input) {
        List<ColorDataSource> colorDataSourceList = getColorList();
        List<ColorDataSource> selectedList = new ArrayList<ColorDataSource>();
        for (ColorDataSource dataSource : colorDataSourceList) {
            if (dataSource.getName().equals(input.toUpperCase())) {
                selectedList.add(new ColorDataSource(dataSource.getName(), dataSource.getColor()));
            }
        }
        return selectedList;
    }

}
