package com.demo.orders.language;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageConfig {
    Context context;


    public LanguageConfig(Context context) {
        this.context = context;

    }

    public void setLanguage(String language_code) {
        Resources res = context.getResources();
// Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(language_code.toLowerCase())); // API 17+ only.
// Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

}
