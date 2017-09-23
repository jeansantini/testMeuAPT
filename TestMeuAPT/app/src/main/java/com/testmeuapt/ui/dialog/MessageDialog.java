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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.testmeuapt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jsantini on 23/09/17.
 */

public class MessageDialog extends DialogFragment {

    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_ERROR = 2;
    private Context mContext;
    private String msg;
    @BindView(R.id.bt_modal_ok)
    Button btOK;
    @BindView(R.id.tv_modal_msg)
    TextView tvMsg;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    private OnModalDialogListener modalDialogListener;
    private int type;

    public static MessageDialog newInstance(final String msg,
                                            final OnModalDialogListener modalDialogListener,
                                            final int type) {
        MessageDialog f = new MessageDialog();
        f.modalDialogListener = modalDialogListener;
        f.msg = msg;
        f.type = type;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.message_dialog, container, false);
        mContext = rootView.getContext();
        ButterKnife.bind(this, rootView);
        initDialog();
        return rootView;
    }

    private void initDialog() {
        tvMsg.setText(msg);
        if(type == TYPE_SUCCESS) {
            ivIcon.setImageDrawable(mContext.getDrawable(R.drawable.ic_check_black_36dp));
        } else {
            ivIcon.setImageDrawable(mContext.getDrawable(R.drawable.ic_error_black_36dp));
        }
        Dialog d = getDialog();
        if (d != null) {
            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (modalDialogListener != null) {
            btOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modalDialogListener.onModalDialogOKClick();
                }
            });
        } else {
            btOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
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

    public interface OnModalDialogListener {
        void onModalDialogOKClick();
    }
}
