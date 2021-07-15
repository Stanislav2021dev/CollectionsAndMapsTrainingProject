package com.example.colmaps;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CollectionsViewModel extends ViewModel {
    private static final MutableLiveData<String[]> timeRes = new MutableLiveData<>();
    private static final MutableLiveData<Boolean[]> pbStatus = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> butStatus = new MutableLiveData<>();
    private static String[] currentTime = new String[24];
    private static Boolean[] currentStatus = new Boolean[24];

    public static String[] getCurrentTime() {
        return currentTime;
    }

    public static void nullifyTimeResult() {
        currentTime = new String[24];
    }

    public static Boolean[] getCurrentStatus() {
        return currentStatus;
    }

    public static void nullifyPbStatus() {
        currentStatus = new Boolean[24];
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

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.v("MyApp", "ONClear");
    }

    static class MyCallableTask implements Callable<Integer> {

        private final int it;
        private final int collections;
        private final Method m0;
        private final Method m1;
        private final Method m2;
        private final Method method;
        private final FillingCollections fillingCollections;
        private final OperationsWithArrayList operationsWithArrayList;
        private final OperationsWithLinkedList operationsWithLinkedList;
        private final OperationsWithCopyOnWriteArrayList operationsWithCopyOnWriteArrayList;
        private long startTime;
        private int operationTime;
        private int index;

        public MyCallableTask(Method method, Method m0, Method m1, Method m2, FillingCollections fillingCollections,
                              OperationsWithArrayList operationsWithArrayList, OperationsWithLinkedList operationsWithLinkedList,
                              OperationsWithCopyOnWriteArrayList operationsWithCopyOnWriteArrayList,
                              int it, int collections) {
            this.collections = collections;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.m2 = m2;
            this.method = method;
            this.operationsWithArrayList = operationsWithArrayList;
            this.operationsWithLinkedList = operationsWithLinkedList;
            this.operationsWithCopyOnWriteArrayList = operationsWithCopyOnWriteArrayList;
            this.fillingCollections = fillingCollections;

        }

        @Override
        public Integer call() {
            try {
                index = (it + 1) + (collections * 8) - 1;
                currentStatus[index] = true;
                CollectionsViewModel.pbStatus.postValue(currentStatus);
                Log.v("MyApp", "index = " + index);
                if (it == 0) {
                    CollectionsViewModel.butStatus.postValue(false);
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingCollections);

                } else {
                    switch (collections) {
                        case (0):
                            FillingCollections.countDownLatch1.await();
                            ArrayList<Integer> copyCol0 =
                                    new ArrayList<>(FillingCollections.colArrayList);
                            startTime = System.currentTimeMillis();
                            m0.invoke(operationsWithArrayList, copyCol0);

                            break;
                        case (1):
                            FillingCollections.countDownLatch2.await();
                            LinkedList<Integer> copyCol1 =
                                    new LinkedList<>(FillingCollections.colLinkedList);
                            startTime = System.currentTimeMillis();
                            m1.invoke(operationsWithLinkedList, copyCol1);

                            break;
                        case (2):
                            FillingCollections.countDownLatch3.await();
                            CopyOnWriteArrayList<Integer>
                                    copyCol2 =
                                    new CopyOnWriteArrayList<>(FillingCollections.colCopyOnWriteArrayList);
                            startTime = System.currentTimeMillis();
                            m2.invoke(operationsWithCopyOnWriteArrayList, copyCol2);
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
                CollectionsViewModel.timeRes.postValue(currentTime);
                CollectionsViewModel.pbStatus.postValue(currentStatus);
                if (!(Arrays.asList(currentTime).contains(null))) {
                    CollectionsViewModel.butStatus.postValue(true);
                }

            } catch (IllegalAccessException | InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
                Log.v("MyApp", "Catch");
            }
            return null;
        }
    }
}


