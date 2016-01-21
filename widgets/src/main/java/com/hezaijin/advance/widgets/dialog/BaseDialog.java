package com.hezaijin.advance.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.hezaijin.advance.widgets.R;


/**
 * BaseDialog 。 可通过setLayoutParams来指定宽高和位置
 */
public abstract class BaseDialog extends Dialog {

    public static final int DEFAULT_WIDTH = 480;
    public static final int DEFAULT_HEIGHT = 320;

    private WindowManager.LayoutParams mParams;
    private int mWidth = DEFAULT_WIDTH;
    private int mHeight = DEFAULT_HEIGHT;
    private int mGravity = Gravity.CENTER;

    public BaseDialog(Context context) {
        super(context, R.style.CustomDialog);
        setContentView(onCreateView(context));
    }


    public abstract View onCreateView(Context context);

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     * @return
     */
    public BaseDialog setLayoutParams(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    /**
     * 设置宽高和gravity
     *
     * @param width
     * @param height
     * @param gravity
     * @return
     */
    public BaseDialog setLayoutParams(int width, int height, int gravity) {
        this.mWidth = width;
        this.mHeight = height;
        this.mGravity = gravity;
        return this;
    }


    public BaseDialog setGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    public BaseDialog setLayoutParams(WindowManager.LayoutParams params) {
        this.mParams = params;
        return this;
    }

    @Override
    public void show() {
        super.show();
        // set window layout params
        if (null == mParams) {
            mParams = getWindow().getAttributes();
            mParams.width = mWidth;
            mParams.height = mHeight;
            mParams.gravity = mGravity;
            mParams.format = PixelFormat.RGBA_8888;
        }
        getWindow().setAttributes(mParams);
    }

}
