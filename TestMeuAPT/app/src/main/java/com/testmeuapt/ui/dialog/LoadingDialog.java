package com.testmeuapt.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.testmeuapt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jsantini on 23/09/17.
 */

public class LoadingDialog extends DialogFragment {

    private Context mContext;
    private String msg;

    @BindView(R.id.tv_loading_msg)
    TextView tvLoadingMsg;

    public static LoadingDialog newInstance(final String msg) {
        LoadingDialog f = new LoadingDialog();
        f.msg = msg;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.loading_dialog, container, false);
        mContext = rootView.getContext();
        ButterKnife.bind(this, rootView);
        initDialog();
        return rootView;
    }

    private void initDialog() {
        Dialog d = getDialog();
        if (d != null) {
            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if(msg != null) {
            tvLoadingMsg.setText(msg);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
