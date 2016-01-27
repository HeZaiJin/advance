package com.hezaijin.advance.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hezaijin.advance.R;
import com.hezaijin.advance.base.BaseActivity;
import com.hezaijin.advance.ui.fragment.EntryFragment;
import com.hezaijin.advance.ui.fragment.ListDataFragment;
import com.hezaijin.advance.utils.Logger;

public class EntryActivity extends BaseActivity implements EntryFragment.onEntryCallback {
    private static final String TAG = "EntryActivity";

    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initEntryFragment();
    }

    private void initEntryFragment(){
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.entry_content, new EntryFragment());
        ft.commit();

    }

    @Override
    public void onEntryEnter() {
        Logger.d(TAG,"onEntryEnter");
    }

    @Override
    public void onEntryExit() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.activity_enter_in,R.anim.activity_enter_out);
        transaction.replace(R.id.entry_content, new ListDataFragment());
        transaction.commit();

        Logger.d(TAG, "onEntryExit");
    }
}
