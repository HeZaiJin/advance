package com.hezaijin.advance.widgets.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

/**
 * @author HaoZhang
 * @date 2016/1/22.
 */
public class ScrollOverLayout extends ScrollView {
    private static final String TAG = "ReboundScrollView";
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 400;
    private Context mContext;
    private int mMaxYOverscrollDistance;

    public ScrollOverLayout(Context context){
        super(context);
        mContext = context;
        initBounceScrollView();
    }

    public ScrollOverLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        mContext = context;
        initBounceScrollView();
    }

    public ScrollOverLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        mContext = context;
        initBounceScrollView();
    }

    private void initBounceScrollView(){
        setOverScrollMode(OVER_SCROLL_ALWAYS );
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        final float density = metrics.density;

        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent){
        //这块是关键性代码
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance, isTouchEvent);
    }

}
