package com.hezaijin.advance.ui.activity;

import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.hezaijin.advance.R;
import com.hezaijin.advance.base.BaseActivity;
import com.hezaijin.advance.utils.Logger;
import com.hezaijin.advance.utils.SystemBarTintManager;
import com.hezaijin.advance.widgets.view.entry.KenBurnsView;
import com.hezaijin.advance.widgets.view.entry.RandomTransitionGenerator;
import com.hezaijin.advance.widgets.view.entry.Transition;

public class EntryActivity extends BaseActivity {
    private static final String TAG = "EntryActivity" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (null!=getActionBar()) {
            getActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        SystemBarTintManager manager = new SystemBarTintManager(this);
        manager.setNavigationBarTintEnabled(true);
        manager.setStatusBarTintEnabled(true);

        KenBurnsView kenBurnsView = (KenBurnsView) findViewById(R.id.burns_view);
        kenBurnsView.setImageDrawable(getResources().getDrawable(R.drawable.bg_entry,null));
        kenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Logger.d(TAG, "onTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Logger.d(TAG, "onTransitionEnd");
            }
        });
        kenBurnsView.resume();
    }

}
