package com.example.bmiapp.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class DataBase {
    private static final String PREFERENCES_NAME = "MyPreferences";
    public static void saveValue(Context context, String s, int v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(s, v);
        editor.apply();
    }

    public static int getValue(Context context,String s, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int v = sharedPreferences.getInt(s, defaultValue);
        return v;
    }

    public static void saveValue(Context context, String s, String v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(s, v);
        editor.apply();
    }

    public static String getValue(Context context,String s, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String v = sharedPreferences.getString(s, defaultValue);
        return v;
    }

    public static void saveValue(Context context, String s, Float v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(s, v);
        editor.apply();
    }

    public static float getValue(Context context, String s, float defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        float v = sharedPreferences.getFloat(s, defaultValue);
        return v;
    }

    public static void saveValue(Context context, String s, boolean v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(s, v);
        editor.apply();
    }

    public static boolean getValue(Context context, String s, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean v = sharedPreferences.getBoolean(s, defaultValue);
        return v;
    }
}
