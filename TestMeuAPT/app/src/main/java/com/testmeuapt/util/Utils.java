package com.testmeuapt.util;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jsantini on 22/09/17.
 */

public class Utils {

    public static Boolean stringIsEmpty(final String string) {
        if (string == null || string.equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static String formatDate(final Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();

            if (null != view && null != view.getWindowToken() && EditText.class.isAssignableFrom(view.getClass())) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } else {
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

}
