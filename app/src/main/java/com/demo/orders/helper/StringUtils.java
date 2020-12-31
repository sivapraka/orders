package com.demo.orders.helper;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.demo.orders.model.OrdersDataSource;

import java.util.List;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by admin on 16-Jul-18.
 */

public class StringUtils {

    public static final String format = "%-15s %10s";
    Context context;

    public StringUtils(Context context) {
        this.context = context;
    }

    public String center(String s, int size) {
        return center(s, size, ' ');
    }

    public String center(String s, int size, char pad) {

        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
