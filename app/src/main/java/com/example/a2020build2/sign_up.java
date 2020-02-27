package com.example.a2020build2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener((View v)->{finish();});
        setSupportActionBar(toolbar);
        ImageView username_sign=findViewById(R.id.username_legal_sign);
        ImageView password_sign=findViewById(R.id.password_legal_sign);
        ImageView password_dup_sign=findViewById(R.id.password_dup_legal_sign);
        ImageView email_sign=findViewById(R.id.email_legal_sign);
        TextView username_legal_tip=findViewById(R.id.username_legal_tip);
        TextView password_legal_tip=findViewById(R.id.password_legal_tip);
        TextView password_dup_legal_tip=findViewById(R.id.password_dup_legal_tip);
        TextView email_legal_tip=findViewById(R.id.email_legal_tip);

        EditText username=findViewById(R.id.sign_up_user_name);
        EditText user_email=findViewById(R.id.sign_up_user_email);
        EditText user_password=findViewById(R.id.sign_up_user_password);
        EditText user_password_dup=findViewById(R.id.sign_up_user_password_dup);
        EditText user_nickname=findViewById(R.id.sign_up_user_nickname);
        EditText user_telephone=findViewById(R.id.sign_up_telephone);
        ProgressBar username_query_progress=findViewById(R.id.username_query_progress);
        ProgressBar email_query_progress=findViewById(R.id.email_query_progress);
        username_sign.setVisibility(ImageView.INVISIBLE);
        password_sign.setVisibility(ImageView.INVISIBLE);
        password_dup_sign.setVisibility(ImageView.INVISIBLE);
        email_sign.setVisibility(ImageView.INVISIBLE);
        username_legal_tip.setVisibility(TextView.INVISIBLE);
        password_legal_tip.setVisibility(TextView.INVISIBLE);
        password_dup_legal_tip.setVisibility(TextView.INVISIBLE);
        email_legal_tip.setVisibility(TextView.INVISIBLE);
        username_query_progress.setVisibility(TextView.INVISIBLE);
        email_query_progress.setVisibility(TextView.INVISIBLE);

        user_password.addTextChangedListener(
                new EditableAdapter (){
                    @Override
                    public void afterTextChanged(Editable s) {
                        super.afterTextChanged(s);
                        if(!isPasswordLegal(user_password.getText().toString())){
                            password_legal_tip.setVisibility(TextView.VISIBLE);
                            password_sign.setVisibility(TextView.VISIBLE);
                        }else{
                            password_legal_tip.setVisibility(TextView.INVISIBLE);
                            password_sign.setImageDrawable(getResources().getDrawable(R.drawable.benefits));
                        }
                    }
                }
        );
    }
    private boolean isPasswordLegal(String password){
        boolean contains[]=new boolean[3];
        for(int i=0;i<password.length();i++){
             if(password.charAt(i)>='a'&&password.charAt(i)>='z'&&password.charAt(i)>='A'&&password.charAt(i)>='Z')contains[0]=true;
        else if(password.charAt(i)>='0'&&password.charAt(i)<='9')contains[1]=true;
        else    contains[2]=true;
        }
        return password.length()>=8&&((contains[0]&&contains[1])||(contains[1]&&contains[2])||(contains[2]&&contains[0]));
    }
}
