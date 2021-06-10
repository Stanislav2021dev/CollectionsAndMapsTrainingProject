package com.example.colmaps;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Maps extends Fragment {
    private EditText num;
    private Button test;
    private ProgressBar[][] myPb;
    private TextView[][] myTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        num = view.findViewById(R.id.numMaps);
        test = view.findViewById(R.id.testMap);
        myPb = new ProgressBar[][]{

                {view.findViewById(R.id.pb1),
                        view.findViewById(R.id.pb2),
                        view.findViewById(R.id.pb3),
                        view.findViewById(R.id.pb4)},

                {view.findViewById(R.id.pb5),
                        view.findViewById(R.id.pb6),
                        view.findViewById(R.id.pb7),
                        view.findViewById(R.id.pb8)}

        };
        myTv = new TextView[][]{
                {view.findViewById(R.id.time1),
                        view.findViewById(R.id.time2),
                        view.findViewById(R.id.time3),
                        view.findViewById(R.id.time4)},
                {view.findViewById(R.id.time5),
                        view.findViewById(R.id.time6),
                        view.findViewById(R.id.time7),
                        view.findViewById(R.id.time8)}

        };


        return view;
    }
}