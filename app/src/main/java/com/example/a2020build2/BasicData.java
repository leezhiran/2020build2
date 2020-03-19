package com.example.a2020build2;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class BasicData extends Application {
    private Bitmap user_avatar;
    private String info[];
    private Bitmap photo[];
    private boolean hasLogged;
    public enum infoIndex{
        UID,USER_EMAIL,NICKNAME,ADMINISTRATOR_OF,MEMBER_OF,EVENT_PARTICIPATED_IN,REGISTER_TIME,PASSWORD
    };

    public enum photo{
        USER_AVATAR
    }
    private static Context mcontext;
    public void onCreate(){
        super.onCreate();
        mcontext=getApplicationContext();
        this.info=new String [9];
        this.photo=new Bitmap[1];
    }
    void setInfo(int index,String input){
        this.info[index]=input;
    }
    void setPhoto(int index,Bitmap input){ this.photo[index]=input;}
    void setHasLogged(boolean staus){
        this.hasLogged=staus;
    }
    static Context getContext(){
        return mcontext;
    }
}
