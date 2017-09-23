package com.testmeuapt.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jsantini on 22/09/17.
 */

public class SharedPreferenceUtil {
    public static final String SHARED_PREFS_IDENTIFIER = "com.testmeuapt";
    public static final String KEY_TOKEN = "meuapt_token";
    public static final String MY_ACCESS_TOKEN =  "502b5c3182b608a47110a594930c68f904b13e3099ac2999f20e1f585ecc6306";

    //Salva dos dados no Shared Preferences
    static public void saveSharedPreferences(Context context, String identifier, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(identifier, context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    //Busca os dados do Shared Preferences
    static public String getSharedPreferences(Context context, String identifier, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(identifier, context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        return value;
    }

    public static void saveAccessToken(final Context context) {
        saveSharedPreferences(context, SHARED_PREFS_IDENTIFIER,
                KEY_TOKEN, MY_ACCESS_TOKEN);
    }

    public static String getAccessToken(final Context context) {
        String token = getSharedPreferences(context, SHARED_PREFS_IDENTIFIER, KEY_TOKEN);
        return token;
    }
}
