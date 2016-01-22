package com.hezaijin.advance.ui.activity;

import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezaijin.advance.R;

public class InterpolateActivity extends AppCompatActivity {


    private TextView mTitle;
    private ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) findViewById(R.id.title);
        mImg = (ImageView) findViewById(R.id.img);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interpolate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Interpolator interpolator  = null;
        CharSequence title  = item.getTitle();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.interpolate_accelerate:
                interpolator = new AccelerateInterpolator();
                break;
            case R.id.interpolate_accelerate_decelerate:
                interpolator = new AccelerateDecelerateInterpolator();
                break;
            case R.id.interpolate_anticipate:
                interpolator  = new AnticipateInterpolator();
                break;
            case R.id.interpolate_anticipate_overshoot:
                interpolator = new AnticipateOvershootInterpolator();
                break;
            case R.id.interpolate_base:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    interpolator = new BaseInterpolator() {
                        @Override
                        public float getInterpolation(float input) {
                            return input*2;
                        }
                    };
                }
                break;
            case R.id.interpolate_cycle:
                interpolator = new CycleInterpolator(50);
                break;
            case R.id.interpolate_bounce:
                interpolator = new BounceInterpolator();
                break;
            case R.id.interpolate_decelerate:
                interpolator = new DecelerateInterpolator();
                break;
            case R.id.interpolate_fast_out_linear:
                interpolator = new FastOutLinearInInterpolator();
                break;
            case R.id.interpolate_fast_out_show:
                interpolator = new FastOutSlowInInterpolator();
                break;
            case R.id.interpolate_linear:
                interpolator = new LinearInterpolator();
                break;
            case R.id.interpolate_linear_out_show:
                interpolator = new LinearOutSlowInInterpolator();
                break;
            case R.id.interpolate_path:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    interpolator = new PathInterpolator(new Path());
                }
                break;
            case R.id.interpolate_over_shoot:
                interpolator = new OvershootInterpolator();
                break;
        }
        setAnimation(title,interpolator);
        return super.onOptionsItemSelected(item);
    }


    public void setAnimation(CharSequence title,Interpolator interpolator){
        if (null == interpolator)return;
        mTitle.setText(title);
        mImg.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(0,0,0,800);
        animation.setInterpolator(interpolator);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        mImg.setAnimation(animation);
        animation.start();

    }

}
