package com.example.binguner.zhijiao.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.binguner.zhijiao.R;

public class ClassTableDetial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getActionBar().hide();
        setContentView(R.layout.activity_class_table_detial);
    }
}
