package com.demo.orders.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by admin on 26-Feb-18.
 */

public class BitmapUtility {
    Context context;

    private String TAG = BitmapUtility.class.getSimpleName();

    public BitmapUtility(Context ctxt) {
        this.context = ctxt;
    }

    // convert from bitmap to byte array
    public byte[] getBytes(Bitmap bitmap) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public Bitmap getImage(byte[] image) throws Exception {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    // Both Conversion (Bitmap-> byte-> bitmap)
    public Bitmap getImageFromBitMap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] bytes = stream.toByteArray();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    // converting png image to byte[]
    public byte[] getImage(int resource) {
        Drawable drawable = context.getResources().getDrawable(resource);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] buffer = stream.toByteArray();
        return buffer;
    }

    //convert byte[] to base64
    public String getBaseImage(byte[] image) {
        String imgString = Base64.encodeToString(image,
                Base64.NO_WRAP);
        return imgString;
    }

    public byte[] getBase64toByteArray(String image) {
        return Base64.decode(image,
                0);
    }

    public byte[] getBitmapFromURL(String src) {
        try {
//            Log.e(TAG, "getBitmapFromURL: "+src );
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(src);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getStringImage(Bitmap bm) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 90, ba);
        byte[] by = ba.toByteArray();
        return Base64.encodeToString(by, Base64.DEFAULT);
    }

    public String base64(byte[] name) {
        return Base64.encodeToString(name, Base64.DEFAULT);

    }

}
