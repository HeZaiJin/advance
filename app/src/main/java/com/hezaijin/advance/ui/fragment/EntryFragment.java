package com.hezaijin.advance.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hezaijin.advance.R;
import com.hezaijin.advance.base.BaseFragment;
import com.hezaijin.advance.utils.Logger;
import com.hezaijin.advance.widgets.view.entry.KenBurnsView;
import com.hezaijin.advance.widgets.view.entry.Transition;

/**
 * @author HaoZhang
 * @date 2016/1/27.
 */
public class EntryFragment extends BaseFragment {
    private static final String TAG = "EntryFragment";
    KenBurnsView kenBurnsView;
    private onEntryCallback mCallback;
    public EntryFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (onEntryCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater,container,savedInstanceState);
    }

    private View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_entry, null, true);
        onInitView(view);
        return view;
    }

    protected void onInitView(View view) {
        kenBurnsView = (KenBurnsView) view.findViewById(R.id.burns_view);
        kenBurnsView.setImageResource(R.drawable.bg_entry);
        kenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                if (null!=mCallback) {
                    mCallback.onEntryEnter();
                }
                Logger.d(TAG, "onTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                if (null!=mCallback) {
                    mCallback.onEntryExit();
                }
                Logger.d(TAG, "onTransitionEnd");
            }
        });
        kenBurnsView.resume();
    }


    public interface onEntryCallback{
        void onEntryEnter();
        void onEntryExit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallback = null;
    }
}
