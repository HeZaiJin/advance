package com.hezaijin.advance.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hezaijin.advance.R;
import com.hezaijin.advance.base.BaseFragment;
import com.hezaijin.advance.ui.dialog.CustomDialog;
import com.hezaijin.advance.ui.fragment.ListDataFragment;

public class MainActivity extends AppCompatActivity {

    public final static String TAG ="Retrofit";
    public final String URL="https://api.github.com";

    private TextView mContent;
    private TextView mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomDialog(MainActivity.this).setLayoutParams(1200,400, Gravity.BOTTOM).show();
            }
        });
        initView();
    }


    private void initView(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new ListDataFragment());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        reset();
        switch (id){
            case R.id.action_retrofit:
                break;
            case R.id.action_volley:
                break;
            case R.id.action_okhttp:
                break;
            case R.id.action_httputil:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void reset(){
//        mContent.setText("");
    }


}
