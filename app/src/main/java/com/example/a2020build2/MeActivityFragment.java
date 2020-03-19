package com.example.a2020build2;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * A placeholder fragment containing a simple view.
 */
public class MeActivityFragment extends Fragment {

    public MeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        System.out.println("OAC");
        Button button = getActivity().findViewById(R.id.sign_up_button);
        Button button1=getActivity().findViewById(R.id.button2);
        button.setOnClickListener((View v)->{startActivity(new Intent(getActivity(),sign_in.class));});
        button1.setOnClickListener((View v)->{startActivity(new Intent(getActivity(),me_detail.class));});
    }
    public void init_connection_sign_up(){
        final String adr="http://192.168.0.105:8080/serverResponse/sign_up";
        new Thread(new Runnable() {
            public void run() {
                try{
                    URL url = new URL(adr);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.connect();
                    String data="user_name=test_user1&user_password=3721qwer&user_nickname=test_nickname&user_e_mail=test@test.com";//test connn
                    OutputStream out= connection.getOutputStream();
                    out.write(data.getBytes());
                    out.flush();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String get_response=null;
                    System.out.println("runto");
                    Looper.prepare();
                    while((get_response=reader.readLine())!=null){
                        if(get_response=="OK"){
                            System.out.println(get_response);
                        }else{
                            System.out.println(get_response);
                        }
                    }
                }catch(IOException mue){
                    mue.printStackTrace();
                }
            }
        }).start();
        Looper.loop();
    }
}
