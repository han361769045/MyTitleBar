package com.lu.leo.mytitlebar.demo;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leo.lu.mytitlebar.MyTitleBar;
import com.leo.statusbar.flyn.Eyes;

/**
 * Created by LeoLu on 2016/12/21.
 */

public class MainActivity extends AppCompatActivity {

    MyTitleBar my_title_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_title_bar = findViewById(R.id.my_title_bar);
        Eyes.translucentStatusBar(MainActivity.this, true, true);
        //        my_title_bar.setLeftTextMarginLeft(50);
        my_title_bar.setLeftTextOnClickListener(v -> Toast.makeText(MainActivity.this, "dfasdfafas", Toast.LENGTH_SHORT).show());
    }
}
