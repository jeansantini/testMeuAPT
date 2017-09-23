package com.testmeuapt.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.testmeuapt.R;
import com.testmeuapt.ui.dialog.LoadingDialog;
import com.testmeuapt.ui.dialog.MessageDialog;
import com.testmeuapt.util.SharedPreferenceUtil;
import com.testmeuapt.util.Utils;

public class BaseActivity extends AppCompatActivity {
    private boolean isActive;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        overridePendingTransition(R.anim.slide_in_rigth_to_left, R.anim.slide_out_rigth_to_left);
        verifyToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    private void verifyToken() {
        if(Utils.stringIsEmpty(SharedPreferenceUtil.getAccessToken(this))) {
            SharedPreferenceUtil.saveAccessToken(this);
        }
    }

    public void showOrHideLoading(final boolean isShowLoading, final String msg) {
        if(isShowLoading) {
            showLoading(msg);
        } else {
            hideLoading();
        }
    }

    private void showLoading(final String msg) {
        Utils.hideKeyboard(this);
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        loadingDialog = LoadingDialog.newInstance(msg);
        loadingDialog.setCancelable(false);
        loadingDialog.show(ft, "dialog");
    }

    private void hideLoading() {
        if(loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_to_rigth, R.anim.slide_out_left_to_rigth);
    }

    public void showGenericError() {
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_generic_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    public void showConnectionError() {
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_connection_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

}
