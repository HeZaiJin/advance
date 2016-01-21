package com.hezaijin.advance.widgets.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 可上下拖拽的viewgroup
 *
 * @author HaoZhang
 * @date 2016/1/21.
 */
public class ScrollableLayout extends ScrollView {
    private static final String TAG = "ScrollableLayout";
    public static final int DEFAULT_HEIGHT = 300;
    public static final int MODE_BOTH = 0;
    public static final int MODE_UNABLE = -1;
    public static final int MODE_ONLY_TOP = 1;
    public static final int MODE_ONLY_BOTTOM = 2;

    private int mMode = MODE_BOTH;
    private int mScrollableTopHeight = DEFAULT_HEIGHT;
    private int mScrollableBottomHeight = DEFAULT_HEIGHT;


    public ScrollableLayout(Context context) {
        this(context,null);
    }

    public ScrollableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
        init(context,attrs);
    }

    /**
     * 初始化
     * @param context context object
     * @param attrs attributeset
     */
    private void init(Context context,AttributeSet attrs){

    }

 /*   @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (!isTouchEvent) { // 禁止惯性滑动
            if ((scrollY < 0 && deltaX < 0)
                    || (scrollY > getHeight() && deltaX > 0)) {
                deltaY = 0;
            }
        }
        return super.overScrollBy(deltaX, (deltaY + 1) / 2, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 400, isTouchEvent);
    }
}
