package com.example.a2020build2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Tools {
    public static byte[] compressBitmap(Bitmap origin,int quality){
        byte[] buffer=null;
        ByteArrayOutputStream baos1= new ByteArrayOutputStream(origin.getByteCount());
        origin.compress(Bitmap.CompressFormat.WEBP,100,baos1);
        try {
            buffer=baos1.toByteArray();
            baos1.close();
        }catch(IOException ie1) {
            ie1.printStackTrace();
        }
        return buffer;
    }
    public static String byteToString(byte[] data){
        String ret= Base64.getEncoder().encodeToString(data);
        return ret;
    }
    public static Bitmap StringToBitmap(String input){
        byte[] convert=Base64.getDecoder().decode(input);
        for(int i=0;i<convert.length;++i) {
            if(convert[i]<0) {
                convert[i]+=256;
            }
        }
        Bitmap b= BitmapFactory.decodeByteArray(convert,0,convert.length);
        return b;
    }
}
