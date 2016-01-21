package com.hezaijin.advance.widgets.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ScrollingView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Gallery;
import android.widget.ScrollView;

/**
 * @author HaoZhang
 * @date 2016/1/21.
 */
public class ScrollHelper {

    interface ScrollHelperImpl {
        boolean canScrollVertically(View v, int direction);
        void scrollVerticalBy(View v, int dy);
    }

    private ScrollHelper() {}

    /** * Check if this view can be scrolled vertically in a certain direction. * * @param v The View against which to invoke the method. * @param direction Negative to check scrolling up, positive to check scrolling down. * @return true if this view can be scrolled in the specified direction, false otherwise. */
    public static boolean canScrollVertically(View v, int direction) {
        return IMPL.canScrollVertically(v, direction);
    }

    public static void scrollVerticalBy(View v, int dy) {
        IMPL.scrollVerticalBy(v, dy);
    }

    static final ScrollHelperImpl IMPL;
    static {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.KITKAT) {
            IMPL = new KitKatScrollHelperImpl();
        } else if (version >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            IMPL = new ICSViewCompatImpl();
        } else {
            IMPL = new BaseScrollHelperImpl();
        }
    }

    static class BaseScrollHelperImpl implements ScrollHelperImpl {

        @Override
        public boolean canScrollVertically(View v, int direction) {
            if (v instanceof ScrollingView) {
                return canScrollingViewScrollVertically((ScrollingView) v, direction);
            } else if (v instanceof AbsListView || v instanceof ScrollView || v instanceof Gallery){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void scrollVerticalBy(View v, int dy) {
            if (v instanceof ScrollView) {
                v.scrollBy(0, dy);
            }
        }

        private boolean canScrollingViewScrollVertically(ScrollingView view, int direction) {
            final int offset = view.computeVerticalScrollOffset();
            final int range = view.computeVerticalScrollRange() -
                    view.computeVerticalScrollExtent();
            if (range == 0) return false;
            if (direction < 0) {
                return offset > 0;
            } else {
                return offset < range - 1;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    static class ICSViewCompatImpl  extends BaseScrollHelperImpl {

        @Override
        public boolean canScrollVertically(View v, int direction) {
            if (v instanceof AbsListView) {
                return canAbsListViewScrollVertically((AbsListView) v, direction);
            } else {
                return v.canScrollVertically(direction);
            }
        }

        private boolean canAbsListViewScrollVertically(AbsListView abslistview, int direction) {
            final int childCount = abslistview.getChildCount();
            if (childCount == 0) {
                return false;
            }

            final int firstPosition = abslistview.getFirstVisiblePosition();
            final int listpaddingbottom = abslistview.getListPaddingBottom();
            final int listpaddingtop = abslistview.getListPaddingTop();
            if (direction > 0) {
                final int lastBottom = abslistview.getChildAt(childCount - 1).getBottom();
                final int lastPosition = firstPosition + childCount;
                return lastPosition < abslistview.getCount() || lastBottom > abslistview.getHeight() - listpaddingbottom;
            } else {
                final int firstTop = abslistview.getChildAt(0).getTop();
                return firstPosition > 0 || firstTop < listpaddingtop;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    static class KitKatScrollHelperImpl extends ICSViewCompatImpl {

        @Override
        public boolean canScrollVertically(View v, int direction) {
            if (v instanceof AbsListView) {
                return ((AbsListView) v).canScrollList(direction);
            } else {
                return super.canScrollVertically(v, direction);
            }
        }

        @Override
        public void scrollVerticalBy(View v, int dy) {
            if (v instanceof AbsListView) {
                ((AbsListView) v).scrollListBy(dy);
            } else {
                super.scrollVerticalBy(v, dy);
            }
        }

    }
}