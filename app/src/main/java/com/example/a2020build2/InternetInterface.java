package com.example.a2020build2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;

public class InternetInterface {
    private static String server_address_root="http://192.168.0.105:8080/serverResponse";
    private static boolean network_result;
    private static Bitmap o;
    public static  boolean network_init_sign_in(String user_name,String user_password,BasicData application_data){
        Runnable r=(()->{try {
            String data="user_name="+user_name
                       +"&user_password="+user_password;
            BufferedReader net_in = init_reader(sendData(init_connection(server_address_root+"/Sign_in"),data));
            String response=null;
            response=net_in.readLine();
            System.out.println(response);
            BasicData user=application_data;
            if(response.equals("OK")) {//登陆成功，初始化数据。
                for (int i=0;(response = net_in.readLine()) != null&&i<=8;i++) {
                    user.setInfo(i,response);
                    System.out.println(response);
                }
                user.setHasLogged(true);
                net_in.close();
                network_result=true;
                System.out.println("net thread");
            }else{
                while((response=net_in.readLine())!=null){
                    System.out.println(response);
                }
                net_in.close();
                network_result=false;
            }
            net_in.close();
        }catch(MalformedURLException mue1){
            mue1.printStackTrace();
        }catch(IOException ioe1){
            ioe1.printStackTrace();
        }});
        Thread netThread=new Thread(r);
        netThread.start();
        try {
            netThread.join();
        }catch(InterruptedException ie2){
            ie2.printStackTrace();
        }
        System.out.println("main thread");
        return network_result;
    }
    public static boolean network_init_sign_up(String username
                                              ,String user_password
                                              ,String user_nickname
                                              ,String user_email
                                              ,String user_telephone
                                              ,String user_university_no){
        Runnable r=(()->{
            try {
                String data = "user_name=" + username
                        + "&user_password=" + user_password
                        + "&user_nick_name=" + user_nickname
                        + "&user_e_mail=" + user_email
                        + "&telephone_number=" + user_telephone
                        + "&user_university_no=" + user_university_no;
                BufferedReader net_in=init_reader(sendData(init_connection(server_address_root+"/sign_up"),data));
                String response=null;
                response=net_in.readLine();
                if(response.equals("OK")){
                     network_result=true;
                }else{
                    network_result=false;
                }
                net_in.close();
            }catch(IOException ioe1){
                ioe1.printStackTrace();
            }
        });
        Thread netThread = new Thread(r);
        netThread.start();
        try {
            netThread.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        return network_result;
    }
    public static Bitmap test(String user_name, byte[] input){
        Runnable r=(()->{
            try{
                String data="user_name="+user_name+
                            "&encoded_data="+Tools.byteToString(input);
                BufferedReader net_in=init_reader(sendData(init_connection(server_address_root+"/testWorkbench"),data));
                String res=net_in.readLine();
                o=Tools.StringToBitmap(res);
            }catch(IOException ie1){
                ie1.printStackTrace();
            }
        });
        Thread t=new Thread(r);
        try {
            t.join();
        }catch(InterruptedException ie2){
            ie2.printStackTrace();
        }
        t.start();
        System.out.println("12344");
        return o;
    }
    public static boolean query_if_there_dup_username_or_email(int type,String input){
        Runnable r=(()->{
            try{
                String data="type="+type
                           +"&input="+input;
                BufferedReader net_in=init_reader(sendData(init_connection(server_address_root+"/dup_query_email_and_username"),data));
                String response=net_in.readLine();

                if(response.equals("OK")) {
                    network_result=true;
                }else{
                    network_result=false;
                }
            }catch(IOException io2){
                io2.printStackTrace();
            }
        });
        Thread netThread=new Thread(r);
        netThread.start();
        try {
            netThread.join();
        }catch(InterruptedException ie1){
            ie1.printStackTrace();
        }
        System.out.println(network_result);
        return network_result;
    }
    private static HttpURLConnection init_connection(String URL_in)throws IOException{
        URL url = new URL(URL_in);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        return connection;
    }
    private static HttpURLConnection sendData(HttpURLConnection connection,String Data) throws IOException{
        OutputStream net_out = connection.getOutputStream();
        net_out.write(Data.getBytes());
        net_out.flush();
        net_out.close();
        return connection;
    }

    private static BufferedReader init_reader(HttpURLConnection connection)throws IOException{
        BufferedReader net_in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return net_in;
    }
}
