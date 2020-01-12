package com.example.a2020build2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.content.Intent;
import androidx.annotation.*;
import androidx.fragment.app.FragmentTabHost;

public class StarterActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_starter);
        new Thread() {
            public void run() {
                try {
                    //等待两秒钟
                    sleep(2000);
                    //跳转进入APP主页面
                    intent = new Intent(StarterActivity.this,FragmentTabHostActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

}
