package com.testmeuapt.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.testmeuapt.application.TestMeuAPTApplication;

/**
 * Created by jsantini on 22/09/17.
 */

public class NetworkUtil {

    /**
     * Checks whether there's an active internet connection at the current moment.
     * @return TRUE if there's internet, FALSE if you're offline.
     */
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) TestMeuAPTApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
