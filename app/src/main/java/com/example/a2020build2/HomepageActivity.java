package com.example.a2020build2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomepageActivity extends AppCompatActivity {
    private HomepageActivityFragment fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        fragment1=new HomepageActivityFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment1, "HomepageFragment")
                    .commit();
        }
        ImageButton btn1=findViewById(R.id.Club_Activity_Banner_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(HomepageActivity.this,"Toast提示消息",Toast.LENGTH_SHORT    );
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });

    }

}
