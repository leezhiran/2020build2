package com.example.a2020build2;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.*;
import javax.net.*;
public class HomepageActivityFragment extends Fragment {

    public HomepageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println(inflater==null);

        return inflater.inflate(R.layout.fragment_homepage, container, false);

    }
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        System.out.println("123");
    }
    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        System.out.println("onT");

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);
        System.out.println("OAC");
        ImageButton button = (ImageButton) getActivity().findViewById(R.id.Club_Activity_Banner_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }


}
