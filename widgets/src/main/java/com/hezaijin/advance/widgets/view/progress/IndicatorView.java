package com.hezaijin.advance.widgets.view.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.hezaijin.advance.widgets.R;

/**
 * Indicator View .
 * Support custom selected and default drawable ,
 * also you can use custom color .
 * However,you should use the same size of them .
 * It is a defect .
 * @author HaoZhang
 */
public class IndicatorView extends View {

    private static final String TAG = "IndicatorView";

    private static final int DEFAULT_COUNT = 1;

    private int mSelectedColor = Color.GRAY;
    private int mDefaultColor = Color.BLACK;
    private Drawable mSelectedDrawable = null;
    private Drawable mDefaultDrawable = null;
    private int mRadius = 10 * DEFAULT_COUNT;
    private int mPadding = 20 * DEFAULT_COUNT;

    private int mCount = DEFAULT_COUNT;
    private int mSelected = DEFAULT_COUNT - 1;
    private int mGravity = DEFAULT_COUNT - 1;
    private Paint mPaint;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        mCount = a.getInteger(R.styleable.IndicatorView_count, DEFAULT_COUNT);
        mSelectedColor = a.getColor(R.styleable.IndicatorView_select_color, mSelectedColor);
        mDefaultColor = a.getColor(R.styleable.IndicatorView_default_color, mDefaultColor);
        mRadius = a.getDimensionPixelSize(R.styleable.IndicatorView_radius, mRadius);
        mPadding = a.getDimensionPixelSize(R.styleable.IndicatorView_padding, mPadding);
        mSelectedDrawable = a.getDrawable(R.styleable.IndicatorView_select_drawable);
        mDefaultDrawable = a.getDrawable(R.styleable.IndicatorView_default_drawable);
        mGravity = a.getInt(R.styleable.IndicatorView_gravity, 0);
        if (null != mSelectedDrawable) {
            mRadius = ((BitmapDrawable) mSelectedDrawable).getBitmap().getWidth() / 2;
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);// 充满
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        float start_y = getHeight() / 2;
        float start_x = 0;

        switch (mGravity) {
            case 0:
                start_x = (width - (mCount - 1) * (mPadding + 2 * mRadius)) / 2;
                break;
            case 1:
                start_x = getWidth() - (getPaddingRight() + (2 * mCount - 1) * mRadius + (mCount - 1) * mPadding);
                break;
            case 2:
                start_x = getPaddingLeft();
                break;
        }

        int i = 0;
        while (i < mCount) {
            if (null != mSelectedDrawable && null != mDefaultDrawable) {
                Bitmap bitmap = null;
                if (i == mSelected) {
                    bitmap = ((BitmapDrawable) mSelectedDrawable).getBitmap();
                } else {
                    bitmap = ((BitmapDrawable) mDefaultDrawable).getBitmap();
                }
                canvas.drawBitmap(bitmap, start_x - mRadius, start_y - mRadius, mPaint);
            } else {
                if (i == mSelected) {
                    mPaint.setColor(mSelectedColor);
                } else {
                    mPaint.setColor(mDefaultColor);
                }
                canvas.drawCircle(start_x, start_y, mRadius, mPaint);
            }
            start_x += mPadding + 2 * mRadius;
            i++;
        }
    }

    /**
     * set total count
     * @param count
     */
    public void setCount(int count) {
        this.mCount = count;
        invalidate();
    }

    /**
     * set selected indicator color
     */
    public void setSelectedColor(int color) {
        this.mSelectedColor = color;
    }

    /**
     * set default indicator color
     */
    public void setDefaultColor(int color) {
        this.mDefaultColor = color;
    }


    public void setSelectIndex(int index) {
        mSelected = index;
        postInvalidate();
    }

    public int getSelectIndex() {
        return mSelected;
    }

    public int getCount() {
        return mCount;
    }
}
