package com.demo.orders.helper;

import android.content.Context;
import android.graphics.Bitmap;

import com.demo.orders.model.ColorDataSource;
import com.demo.orders.util.BitmapUtility;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 03-Apr-18.
 */

public class UserImage {
    Context context;
    StaticData staticData;
    BitmapUtility bitmapUtility;
    CircleImage circleImage;

    public UserImage(Context context) {
        this.context = context;
        staticData = new StaticData(context);
        bitmapUtility = new BitmapUtility(context);
        circleImage = new CircleImage(context);
    }

    //get the First letter from the string
    private String getFirstLetter(String input) {
        return input.substring(0, 1);
    }

    public byte[] getImage(String custName) throws Exception {
        //get the First Letter
        String firstLetter = getFirstLetter(custName.trim());
        //get the color for the user_not_used input from the resource file
        List<ColorDataSource> userColorList = staticData.getSelectedName(firstLetter);

        String userName = userColorList.get(0).getName();
        int userColor = userColorList.get(0).getColor();
        //Get the bitmap image
        Bitmap bmp = null;
        try {
            bmp = circleImage.circleBackground(context.getResources().getColor(userColor), userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] result = bitmapUtility.getBytes(bmp);
        assert bmp != null;
        bmp.recycle();
        //Convert the bitmap to byte to Store the image in sqlite database
        return result;
    }
}
