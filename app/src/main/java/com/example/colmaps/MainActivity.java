package com.example.colmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MyApp", "on create activity ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 pager = (ViewPager2) findViewById(R.id.viewpager);
        FragmentStateAdapter pageAdapter = new MyAdapter(this);
        pager.setAdapter(pageAdapter);


    }


    public class MyAdapter extends FragmentStateAdapter {
        public MyAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            switch (position){
                case 0:return (Collections.newInstance(position));

                case 1:return (Maps.newInstance(position));

                default:
                     return(Collections.newInstance(position));
            }

            }

            @Override
            public int getItemCount () {
                return 2;
            }
        }
    }





