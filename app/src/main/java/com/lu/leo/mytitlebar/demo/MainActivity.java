package com.lu.leo.mytitlebar.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leo.lu.mytitlebar.MyTitleBar;

/**
 * Created by LeoLu on 2016/12/21.
 */

public class MainActivity extends AppCompatActivity {

    MyTitleBar my_title_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_title_bar = (MyTitleBar) findViewById(R.id.my_title_bar);

//        my_title_bar.setLeftTextMarginLeft(50);

    }
}