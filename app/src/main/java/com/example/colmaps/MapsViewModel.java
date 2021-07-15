package com.example.colmaps;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MapsViewModel extends ViewModel {
    private static final MutableLiveData<String[]> timeRes = new MutableLiveData<>();
    private static final MutableLiveData<Boolean[]> pbStatus = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> butStatus = new MutableLiveData<>();
    private static String[] currentTime = new String[8];
    private static Boolean[] currentStatus = new Boolean[8];

    public static String[] getCurrentTime() {
        return currentTime;
    }

    public static void nullifyTimeResult() {
        currentTime = new String[8];
    }

    public static Boolean[] getCurrentStatus() {
        return currentStatus;
    }

    public static void nullifyPbStatus() {
        currentStatus = new Boolean[8];
    }

    public MutableLiveData<String[]> getRes() {
        timeRes.getValue();
        Log.v("MyApp", "timeRes=" + Arrays.toString(timeRes.getValue()));
        return timeRes;
    }

    public MutableLiveData<Boolean[]> getStatus() {
        pbStatus.getValue();
        return pbStatus;
    }

    public MutableLiveData<Boolean> getButStatus() {
        butStatus.getValue();
        return butStatus;
    }


    static class MyCallableTask implements Callable<Integer> {

        private final int it;
        private final int maps;
        private final Method m0;
        private final Method m1;
        private final Method method;
        private final FillingMaps fillingMaps;
        private final OperationsWithHashMap operationsWithHashMap;
        private final OperationsWithTreeMap operationsWithTreeMap;
        private long startTime;
        private int operationTime;
        private int index;

        public MyCallableTask(Method method, Method m0, Method m1, FillingMaps fillingMaps,
                              OperationsWithHashMap operationsWithHashMap, OperationsWithTreeMap operationsWithTreeMap,
                              int it, int maps) {
            this.maps = maps;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.method = method;
            this.fillingMaps = fillingMaps;
            this.operationsWithHashMap = operationsWithHashMap;
            this.operationsWithTreeMap = operationsWithTreeMap;

        }

        @Override
        public Integer call() {
            try {
                index = (it + 1) + (maps * 4) - 1;
                currentStatus[index] = true;
                MapsViewModel.pbStatus.postValue(currentStatus);
                if (it == 0) {
                    MapsViewModel.butStatus.postValue(false);
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingMaps);
                } else {
                    switch (maps) {
                        case (0):
                            FillingMaps.countDownLatch1.await();
                            HashMap<Integer, Integer> copyMap0 = new HashMap<>(FillingMaps.hashMap);
                            startTime = System.currentTimeMillis();
                            m0.invoke(operationsWithHashMap, copyMap0);
                            break;
                        case (1):
                            FillingMaps.countDownLatch2.await();
                            TreeMap<Integer, Integer> copyMap1 = new TreeMap<>(FillingMaps.treeMap);
                            startTime = System.currentTimeMillis();
                            m1.invoke(operationsWithTreeMap, copyMap1);
                            break;
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                operationTime = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                currentTime[Integer.parseInt(String.valueOf(index))] =
                        String.valueOf(operationTime);
                Log.d("MyApp", "ResultTime = " + Arrays.toString(currentTime));
                currentStatus[index] = false;
                MapsViewModel.timeRes.postValue(currentTime);
                MapsViewModel.pbStatus.postValue(currentStatus);
                if (!(Arrays.asList(currentTime).contains(null))) {
                    MapsViewModel.butStatus.postValue(true);
                }


            } catch (IllegalAccessException | InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
