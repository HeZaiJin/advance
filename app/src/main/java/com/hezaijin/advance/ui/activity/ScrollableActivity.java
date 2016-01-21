package com.hezaijin.advance.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hezaijin.advance.R;

public class ScrollableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrollable);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
/*        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
