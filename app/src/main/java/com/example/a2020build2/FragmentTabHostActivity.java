package com.example.a2020build2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TabHost;
import android.widget.*;
import android.graphics.Color;
import android.view.LayoutInflater;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.BindView;
public class FragmentTabHostActivity extends AppCompatActivity {
    @BindView(android.R.id.tabs)
    TabWidget tabs;

    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;

    final int tabCount=5;
    //private FragmentTabHost tabHost=new FragmentTabHost();
    public String[] tabsTitle = new String[]{"首页","动态","活动","社团","我"};
    private Class[] myFragmentClasses = new Class[]{HomepageActivityFragment.class,DynamicActivityFragment.class
            ,ActivitiesActivityFragment.class,ClubActivityFragment.class,MeActivityFragment.class};
    //Add fragments later here
    private int[] selectorImg = new int[]
            {R.drawable.mainpage,
                    R.drawable.view,
                    R.drawable.notification,
                    R.drawable.like,
                    R.drawable.user};
    //Add imgs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        tabHost=findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        ButterKnife.bind(this);
        for(int i=0;i<tabCount;i++){
            TabHost.TabSpec tabSpec=tabHost.newTabSpec(i+" ").setIndicator(getIndicatorView(i));
            tabHost.addTab(tabSpec,myFragmentClasses[i],null);
        }
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String s){
                TabChange(s);
            }
        });
    }
    private void TabChange(String s){
        int tabIndex=tabHost.getCurrentTab();
        for(int i=0;i<tabCount;i++){
            View view = tabHost.getTabWidget().getChildAt(i);
            ImageView ivTab=view.findViewById(R.id.ivTab);
            TextView tvTab=view.findViewById(R.id.tvTab);
            if (i==tabIndex){
                ivTab.setColorFilter(Color.GREEN);
                tvTab.setTextColor(Color.GREEN);
            }else {
                ivTab.setColorFilter(Color.WHITE);
                tvTab.setTextColor(Color.WHITE);
            }
        }
    }
    private View getIndicatorView(int i) {

        View view = LayoutInflater.from(this).inflate(R.layout.main_tab_item, null, false);
        ImageView ivTab=view.findViewById(R.id.ivTab);
        TextView tvTab=view.findViewById(R.id.tvTab);

        ivTab.setImageResource(selectorImg[i]);
        tvTab.setText(tabsTitle[i]);
        if (i==0){
            ivTab.setColorFilter(Color.GREEN);
            tvTab.setTextColor(Color.GREEN);
        }
        return  view;
    }
}