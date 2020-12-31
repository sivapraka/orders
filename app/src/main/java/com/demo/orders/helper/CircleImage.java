package com.demo.orders.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.demo.orders.R;

import java.io.IOException;

import androidx.core.content.res.ResourcesCompat;

/**
 * Created by admin on 03-Apr-18.
 */

public class CircleImage {
    public Context context;

    public CircleImage(Context context) {
        this.context = context;
    }

    public Bitmap circleBackground(int color, String customText) throws IOException {

        // Create a Paint object for underlined text.
        // The Paint clas offers a full complement of typographical styling methods.
        Paint mPaintText = new Paint();
        // Bounding box for text.
        Rect mBounds = new Rect();
        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                500, // Width
                470, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);

        // Draw a solid color to the canvas background
        canvas.drawColor(Color.TRANSPARENT);
        mPaintText.setColor(
                ResourcesCompat.getColor(context.getResources(),
                        R.color.colorWhite, null)
        );
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        mPaintText.setTextSize(220);
        mPaintText.setAntiAlias(false);
        mPaintText.setDither(false);
        // Initialize a new Paint instance to draw the CircleImage
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setDither(true);

        // Calculate the available radius of canvas
        int radius = Math.min(canvas.getWidth(), canvas.getHeight() / 2);

        // Set a pixels value to padding around the circle
        int padding = 1;

        int vWidth = canvas.getWidth();
        int vHeight = canvas.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;
        // Finally, draw the circle on the canvas
        canvas.drawCircle(
                halfWidth, // cx
                halfHeight, // cy
                radius - padding, // Radius
                paint // Paint
        );
        // Get bounding box for text to calculate where to draw it.
        mPaintText.getTextBounds(customText, 0, customText.length(), mBounds);
        // Calculate x and y for text so it's centered.
        int x = halfWidth - mBounds.centerX();
        int y = halfHeight - mBounds.centerY();
        canvas.drawText(customText, x, y, mPaintText);
        return bitmap;
        // Display the newly created bitmap on app interface
    }
}
