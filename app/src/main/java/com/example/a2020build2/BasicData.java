package com.example.a2020build2;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class BasicData extends Application {
    private Bitmap user_avatar;
    private String info[];
    private boolean hasLogged;
    private static Context mcontext;
    public void onCreate(){
        super.onCreate();
        mcontext=getApplicationContext();
        this.info=new String [9];
    }
    /*0 UID,1 username,2 user_email,3 nickname,4admini of,5 member of ,6event particin,7reg time,8 pwd*/
    void setInfo(int index,String input){
        this.info[index]=input;
    }
    void setHasLogged(boolean staus){
        this.hasLogged=staus;
    }
    static Context getContext(){
        return mcontext;
    }
}
