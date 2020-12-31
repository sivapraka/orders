package com.demo.orders.helper;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;
import com.demo.orders.R;

/**
 * Created by admin on 04-Apr-18.
 */

public class CustomAnimation {
    private Context context;
    private int lastPosition = -1;

    public CustomAnimation(Context context) {
        this.context = context;
    }

    public void setAnimation(View viewToAnimate, int position, int lastPosition) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    //Apply the Slide Bottom to Top animation for the Respective view
    public void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            //Start the animation for  the respective view
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    //Apply the Slide Left to Right animation for the Respective view
    public void setAnimationLeft(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left_to_right);
            //Start the animation for  the respective view
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    //Apply the Slide Right to Left animation for the Respective view
    public void setAnimationRight(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right_to_left);
            //Start the animation for  the respective view
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    //Show the Custom SnackBar (Show the bottom Toast using SnakeBar)
    public void customSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
