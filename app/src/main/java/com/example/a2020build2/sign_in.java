package com.example.a2020build2;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class sign_in extends AppCompatActivity {
    boolean log_status=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ProgressBar progressBar= (ProgressBar) findViewById(R.id.sign_in_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button sign_in_button=findViewById(R.id.sign_in_button);
        Button sign_up_button=findViewById(R.id.sign_up_button);
        progressBar.setVisibility(View.INVISIBLE);
        toolbar.setNavigationOnClickListener((View v)->{finish();});
        sign_in_button.setOnClickListener((View v)->{
                                                    progressBar.setVisibility(ProgressBar.VISIBLE);
                                                    if(InternetInterface.network_init_sign_in(
                                                            ((TextView)findViewById(R.id.sign_in_user_name)).getText().toString(),
                                                            ((TextView)findViewById(R.id.sign_in_password)).getText().toString(),
                                                            (BasicData)getApplication()
                                                    )==false){
                                                        Snackbar.make(v, "登陆失败", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                                                    }
                                                    else {
                                                        Snackbar.make(v, "登陆成功", Snackbar.LENGTH_LONG)
                                                                .setAction("Action", null).show();
                                                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                                                        finish();
                                                    }
                                                    //wait(1000);

    });
        sign_up_button.setOnClickListener((View v)->startActivity(new Intent(this,sign_up.class)));
    }
    private void network_init_sign_in(){
        boolean b;
        EditText text_username=findViewById(R.id.sign_in_user_name),text_password=findViewById(R.id.sign_in_password);
        ProgressBar progressBar= (ProgressBar) findViewById(R.id.sign_in_progress);
        progressBar.setVisibility(View.VISIBLE);
        new Thread(()->{try {
            final String server_adr="http://192.168.0.105:8080/serverResponse/Sign_in";
            URL url = new URL(server_adr);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            String data="user_name="+text_username.getText()+"&user_password="+text_password.getText();
            OutputStream net_out = connection.getOutputStream();
            net_out.write(data.getBytes());
            net_out.flush();
            BufferedReader net_in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response=null;
            response=net_in.readLine();
            System.out.println(response);
            BasicData user=(BasicData)getApplication();

            if(response.equals("OK")) {//登陆成功，初始化数据。
                for (int i=0;(response = net_in.readLine()) != null&&i<=8;i++) {
                    user.setInfo(i,response);
                    System.out.println(response);
                }
                user.setHasLogged(true);
                net_out.close();
                net_in.close();
                log_status=true;
            }else{
                while((response=net_in.readLine())!=null){
                    System.out.println(response);
                }
                net_out.close();
                net_in.close();
                log_status=false;
            }
        }catch(MalformedURLException mue1){
            mue1.printStackTrace();
        }catch(IOException ioe1){

            ioe1.printStackTrace();
        }}).start();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
