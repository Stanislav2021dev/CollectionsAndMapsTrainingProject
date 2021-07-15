package com.example.colmaps;

import android.app.Application;
import android.content.Context;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
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

public  class CollectionsViewModel  extends ViewModel {
    private static MutableLiveData<String[]> timeRes=new MutableLiveData<>();
    private static MutableLiveData<Boolean[]> pbStatus=new MutableLiveData<>();
   // private static String[] currentTime=new String[24];
   // private static Boolean[] currentStatus=new Boolean[24];

    static class MyCallableTask implements Callable<Integer> {

        private final int it;
        private final int collections;
        private final Method m0;
        private final Method m1;
        private final Method m2;
        private final Method method;
        private final FillingCollections fillingCollections;
        private final ArrayListOperations arraylistOperations;
        private final LinkedListOperations linkedListOperations;
        private final CopyOnWriteArrayListOperations copyOnWriteArrayListOperations;
        private long startTime;
        private int operationTime;
        private int index;

        private Singletone s;



        public MyCallableTask(Method method, Method m0, Method m1, Method m2, FillingCollections fillingCollections,
                              ArrayListOperations arraylistOperations, LinkedListOperations linkedListOperations,
                              CopyOnWriteArrayListOperations copyOnWriteArrayListOperations,
                              int it, int collections) {
            this.collections = collections;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.m2 = m2;
            this.method = method;
            this.arraylistOperations = arraylistOperations;
            this.linkedListOperations = linkedListOperations;
            this.copyOnWriteArrayListOperations = copyOnWriteArrayListOperations;
            this.fillingCollections = fillingCollections;
            s = Singletone.getInstance();
        }

        @Override
        public Integer call() {
            s = Singletone.getInstance();
            try {
                index=(it + 1) + (collections * 8) - 1;
                //currentStatus[index]=true;
                //CollectionsViewModel.pbStatus.postValue(currentStatus);
                s.status[index]=true;
                CollectionsViewModel.pbStatus.postValue(s.status);
                Log.v("MyApp", "index = "+index);
                if (it == 0) {
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingCollections);
                } else {
                    switch (collections) {
                        case (0):
                            FillingCollections.countDownLatch1.await();
                            ArrayList<Integer> copyCol0 =
                                    new ArrayList<>(FillingCollections.colArrayList);
                            startTime = System.currentTimeMillis();
                            m0.invoke(arraylistOperations, copyCol0);

                            break;
                        case (1):
                            FillingCollections.countDownLatch2.await();
                            LinkedList<Integer> copyCol1 =
                                    new LinkedList<>(FillingCollections.colLinkedList);
                            startTime = System.currentTimeMillis();
                            m1.invoke(linkedListOperations, copyCol1);

                            break;
                        case (2):
                            FillingCollections.countDownLatch3.await();
                            CopyOnWriteArrayList<Integer>
                                    copyCol2 =
                                    new CopyOnWriteArrayList<>(FillingCollections.colCopyOnWriteArrayList);
                            startTime = System.currentTimeMillis();
                            m2.invoke(copyOnWriteArrayListOperations, copyCol2);
                            break;
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                operationTime = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                s.result[Integer.parseInt(String.valueOf(index))] = String.valueOf(operationTime);
                Log.d("MyApp","ResultTime = "+ Arrays.toString(s.result));
                s.status[index]=false;
                CollectionsViewModel.timeRes.postValue(s.result);
                CollectionsViewModel.pbStatus.postValue(s.status);

            } catch (IllegalAccessException | InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
                Log.v("MyApp", "Catch");
            }
            return null;
        }
    }

    //public static String[] getCurrentTime(){
    //    return currentTime;
   // }
   // public static void nullifyTimeResult(){
    //    currentTime=new String[24];
   // }

    public  MutableLiveData<String[]> getRes(){
      //  timeRes=new MutableLiveData<>();
        timeRes.getValue();
        Log.v("MyApp", "timeRes="+Arrays.toString(timeRes.getValue()));
        return timeRes;
    }

    public  MutableLiveData<Boolean[]> getStatus(){
      //  pbStatus=new MutableLiveData<>();
        pbStatus.getValue();
        return pbStatus;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.v("MyApp","ONClear");
    }

    // public static Boolean[] getCurrentStatus(){
   //     return currentStatus;
 // }


  //  public static void nullifyPbStatus(){
   //     currentStatus=new Boolean[24];
   // }




}


