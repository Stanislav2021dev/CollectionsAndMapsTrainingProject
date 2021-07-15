package com.example.colmaps;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import butterknife.BindViews;

public class Singletone {
    private static Singletone ourInstance = new Singletone();

    int numElementsCollection;
    int numElementsMap;
    boolean butFree=true;

    public static Singletone getInstance() {
        return ourInstance;
    }
}
