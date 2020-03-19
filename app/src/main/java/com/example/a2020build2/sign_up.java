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
import android.widget.Button;
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
        Button sign_up_button=findViewById(R.id.sign_up_action_button);
        boolean conditions[]=new boolean[4];

        user_password.addTextChangedListener(
                new EditableAdapter (){
                    @Override
                    public void afterTextChanged(Editable s) {
                        super.afterTextChanged(s);
                        if(!isPasswordLegal(user_password.getText().toString())){
                            conditions[0]=false;
                            password_sign.setImageDrawable(getResources().getDrawable(R.drawable.caution));
                            password_legal_tip.setVisibility(TextView.VISIBLE);
                            password_sign.setVisibility(TextView.VISIBLE);
                        }else{
                            conditions[0]=true;
                            password_legal_tip.setVisibility(TextView.INVISIBLE);
                            password_sign.setImageDrawable(getResources().getDrawable(R.drawable.benefits));
                        }
                    }
                }
        );
        user_password_dup.addTextChangedListener(
                new EditableAdapter(){
                    @Override
                    public void afterTextChanged(Editable s){
                        super.afterTextChanged(s);
                        if(!user_password.getText().toString().equals(user_password_dup.getText().toString())){
                            conditions[1]=false;
                            password_dup_sign.setImageDrawable(getResources().getDrawable(R.drawable.caution));
                            password_dup_legal_tip.setVisibility(TextView.VISIBLE);
                            password_dup_sign.setVisibility(TextView.VISIBLE);
                        }else{
                            conditions[1]=true;
                            password_dup_legal_tip.setVisibility(TextView.INVISIBLE);
                            password_dup_sign.setImageDrawable(getResources().getDrawable(R.drawable.benefits));
                        }
                    }
                }
        );
        username.addTextChangedListener(
                new EditableAdapter(){
                    @Override
                    public void afterTextChanged(Editable s){
                        super.afterTextChanged(s);
                        if(!InternetInterface.query_if_there_dup_username_or_email(0,username.getText().toString())){
                            conditions[2]=false;
                            username_sign.setImageDrawable(getResources().getDrawable(R.drawable.caution));
                            username_legal_tip.setVisibility(TextView.VISIBLE);
                            username_sign.setVisibility(ImageView.VISIBLE);
                        }else{
                            conditions[2]=true;
                            username_legal_tip.setVisibility(TextView.INVISIBLE);
                            username_sign.setImageDrawable(getResources().getDrawable(R.drawable.benefits));
                            username_sign.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
        user_email.addTextChangedListener(
                new EditableAdapter(){
                    public void afterTextChanged(Editable s){
                        super.afterTextChanged(s);
                        if(!InternetInterface.query_if_there_dup_username_or_email(1,user_email.getText().toString())){
                            conditions[3]=false;
                            email_sign.setImageDrawable(getResources().getDrawable(R.drawable.caution));
                            email_legal_tip.setVisibility(TextView.VISIBLE);
                            email_sign.setVisibility(ImageView.VISIBLE);
                        }else{
                            conditions[3]=true;
                            email_legal_tip.setVisibility(TextView.INVISIBLE);
                            email_sign.setImageDrawable(getResources().getDrawable(R.drawable.benefits));
                            username_sign.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
        sign_up_button.setOnClickListener((v)->{
            if(conditions[0]&&conditions[1]&&conditions[2]&&conditions[3]){
                if(InternetInterface.network_init_sign_up(
                          username.getText().toString(),
                        user_password.getText().toString(),
                        user_nickname.getText().toString(),
                        user_email.getText().toString(),
                        user_telephone.getText().toString(),
                        "000"
                )==false){
                    Snackbar.make(v, "注册失败", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(v, "注册成功", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    try {
                        wait(1000);
                    }catch(InterruptedException ie1){
                        ie1.printStackTrace();
                    }
                };
            }
        });
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
