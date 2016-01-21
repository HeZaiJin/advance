package com.hezaijin.advance.widgets.view;


import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;


/**
 * @author HaoZhang
 * @date 2016/1/21.
 */
public class BounceFrameLayout extends FrameLayout {

    /** * bounce effect only available on ICS and above */
    private static boolean BOUNCE_EFFECT = true;
    private static final int BS_IDLE = 1;
    private static final int BS_DRAG = 2;
    private static final int BS_SETTLE = 3;
    private static final int BS_WAIT = 4;
    private static final int BOUNCE_BACK_DURATION = 250; //ms
    private PointF mInitMotionPoint = new PointF();
    private PointF mLastMotionPoint = new PointF();
    private PointF mOffset = new PointF();
    private final int TOUCH_SLOP;
    private int mBounceState = BS_IDLE;
    private Scroller mScroller;
    /** * whether the view inside received a real down evnt */
    private boolean receivedRealDown;
    /** * the view we want to test scollability, may be null */
    private View capturedView;
    /** * should we cancel the fake down event. its just a * workarund to reduce the incidents rate of unnecessary * click event */
    private boolean shouldCancelFakeDown;

    public BounceFrameLayout(Context context) {
        this(context, null);
    }

    public BounceFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceFrameLayout(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final ViewConfiguration conf = ViewConfiguration.get(getContext());
        TOUCH_SLOP = (conf.getScaledTouchSlop() + 1) / 2;
        mScroller = new Scroller(getContext(),
                new AccelerateDecelerateInterpolator());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!BOUNCE_EFFECT) {
            return super.dispatchTouchEvent(ev);
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (shouldCancelFakeDown && mBounceState == BS_IDLE) {
                    // we must cancel the fake down event to prevent
                    // unnecessary click
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(ev);
                }
                if (getScrollY() != 0) {
                    setBounceState(BS_SETTLE);
                    mScroller.startScroll(getScrollX(), getScrollY(), 0,
                            -getScrollY(), BOUNCE_BACK_DURATION);
                    ViewCompat.postInvalidateOnAnimation(this);
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_DOWN:
                saveInitMotion(ev);
                View onlychild = getChildAt(0);
                capturedView = findScrollableTopChildUnder(onlychild,
                        (int) (ev.getX() + getScrollX() - onlychild.getLeft()),
                        (int) (ev.getY() + getScrollY() - onlychild.getTop()));
                if (mBounceState == BS_SETTLE) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    mScroller.forceFinished(true);
                    setBounceState(BS_DRAG);
                    return true;
                } else {
                    receivedRealDown = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                processMotionMove(ev);
                int idy = (int) (mOffset.y/2);
                final int curState = mBounceState;
                switch (curState) {
                    case BS_DRAG: {
                        int ybefscroll = getScrollY();
                        int yaftscroll = getScrollY() - idy;
                        if (ybefscroll != 0 && ybefscroll * yaftscroll <= 0) {
                            idy = getScrollY();
                        }
                        this.scrollBy(0, -idy);
                        if (getScrollY() == 0) {
                            setBounceState(BS_IDLE);
                            if (!receivedRealDown && canScroll(capturedView, 0, idy)) {
                                shouldCancelFakeDown = true;
                                saveInitMotion(ev);
                                // user may scroll the scrollable view inside , so
                                // we dispatch a fake down event to let the scrollable
                                // view pareparing for scrolling. but we don't know
                                // when should we cancel it exacly
                                ev.setAction(MotionEvent.ACTION_DOWN);
                            }
                        } else {
                            return true;
                        }
                    }
                    break;
                    case BS_WAIT: {
                        // if idy == 0, canScroll cannot decide which direction to scroll, so
                        // just ignore it when state is BS_WAIT, this is ok because user end
                        // up with a fing, then state is in BS_WAIT, the next event sequence
                        // may cause idy == 0
                        if (idy != 0) {
                            final boolean canScroll = canScroll(capturedView, 0, idy);
                            if (!canScroll) {
                                setBounceState(BS_DRAG);
                                this.scrollBy(0, -idy);
                                if (receivedRealDown) {
                                    receivedRealDown = false;
                                    ev.setAction(MotionEvent.ACTION_CANCEL);
                                }
                            }
                        }
                    }
                    break;
                    case BS_IDLE: {
                        final int offsetYFromInit = (int) (ev.getY() - mInitMotionPoint.y);
                        final int offetXFromInit = (int) (ev.getX() - mInitMotionPoint.x);
                        if (Math.abs(offsetYFromInit) > TOUCH_SLOP &&
                                Math.abs(offsetYFromInit) > Math.abs(offetXFromInit)) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                            final boolean canScroll = canScroll(capturedView, 0, idy);
                            if (canScroll) {
                                setBounceState(BS_WAIT);
                                if (shouldCancelFakeDown) {
                                    // scroll the view to let it cancel the down event
                                    // may not work
                                    ScrollHelper.scrollVerticalBy(capturedView, idy);
                                    shouldCancelFakeDown = false;
                                }
                            } else {
                                setBounceState(BS_DRAG);
                                this.scrollBy(0, -idy);
                                if (receivedRealDown) {
                                    receivedRealDown = false;
                                    // we will be in BS_DRAG, so we dispatch cancel
                                    // event to scrollable view
                                    ev.setAction(MotionEvent.ACTION_CANCEL);
                                }
                            }
                        }
                    }
                    break;
                    default:
                        break;
                }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void saveInitMotion(MotionEvent ev) {
        mInitMotionPoint.set(ev.getX(), ev.getY());
        mLastMotionPoint.set(ev.getX(), ev.getY());
    }

    private void processMotionMove(MotionEvent ev) {
        mOffset.set(ev.getX() - mLastMotionPoint.x, ev.getY() - mLastMotionPoint.y);
        mLastMotionPoint.set(ev.getX(), ev.getY());
    }

    private void setBounceState(int state) {
        mBounceState = state;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            if (mScroller.isFinished() || getScrollY() == 0) {
                mScroller.abortAnimation();
                setBounceState(BS_IDLE);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /** * Tests scrollability of view v given a delta of dy. * * The canScrollVertically method of AbsListView cannot report scrollability correctly * when clipToPadding==false, so we call canScrollList when the v is AbsListView and * api level is greater or equal than KITKAT * * @param v View to test for vertical scrollability * @param dx Delta scrolled in pixels along the X axis (not used yet) * @param dy Delta scrolled in pixels along the Y axis * @return true if v can be scrolled by delta of dy. if v==null, false will return */
    protected boolean canScroll(View v, int dx, int dy) {
        if (v == null) {
            return false;
        }
        return ScrollHelper.canScrollVertically(v, -dy);
    }

    /** * Find the topmost scrollable view in the view (include itself) within the parent * view's coordinate system recursively. * * @param x X position to test in the parent's coordinate system * @param y Y position to test in the parent's coordinate system * @return The topmost scrollable view under (x, y) or null if none found. */
    protected View findScrollableTopChildUnder(View view, int x, int y) {
        if (view != null) {
            if (ScrollHelper.canScrollVertically(view, -1) || ScrollHelper.canScrollVertically(view, 1)) {
                return view;
            } else if (view instanceof ViewGroup) {
                final ViewGroup group = (ViewGroup) view;
                final int scrollx = group.getScrollX();
                final int scrolly = group.getScrollY();
                final int childCount = group.getChildCount();
                for (int i = childCount - 1; i >= 0; i--) {
                    final View child = group.getChildAt(i);
                    if (scrollx + x >= child.getLeft() && scrollx + x < child.getRight() &&
                            scrolly + y >= child.getTop() && scrolly + y < child.getBottom()) {
                        return findScrollableTopChildUnder(child, x + scrollx - child.getLeft(),
                                y + scrolly - child.getTop());
                    }
                }
            }
        }
        return null;
    }
}