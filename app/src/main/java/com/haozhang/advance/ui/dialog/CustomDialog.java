package com.haozhang.advance.ui.dialog;

import android.content.Context;
import android.view.View;

import com.haozhang.advance.base.BaseDialog;
import com.haozhang.retrofit.R;

public class CustomDialog extends BaseDialog {
    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(Context context) {
        View view = View.inflate(context, R.layout.custom_dialog_layout, null);
        return view;
    }
}
