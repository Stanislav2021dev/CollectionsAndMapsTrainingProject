package com.example.colmaps;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

class Rand {
    public static int randomInRange(int numEl) {
        int randomEl = (int) (Math.random() * numEl);
        return randomEl;
    }

    public static Integer randomInt() {
        Random element = new Random();
        int newEl = element.nextInt();
        return newEl;
    }
}


class ArraylistOperations {
    public static ArrayList<Integer> colArrayList = new ArrayList<>();
    private Singletone s;

    public void A_FillCollection() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElements; i++) {
            colArrayList.add(Rand.randomInt());
        }
        System.out.println("0-1: size " + colArrayList.size());
    }

    public void B_AddInBeginning() {
        colArrayList.add(0, Rand.randomInt());
    }

    public void C_AddInMiddle() {
        colArrayList.add(Rand.randomInRange(colArrayList.size()), Rand.randomInt());
    }

    public void D_AddInEnd() {
        colArrayList.add(Rand.randomInt());
    }

    public void E_Search() {
        Integer searchEL = Rand.randomInt();
        for (Integer val : colArrayList) {
            if (searchEL.equals(val)) {
                break;
            }
        }
    }

    public void F_RemoveFromBegining() {
        colArrayList.remove(0);
    }

    public void G_RemoveInMiddle() {
        colArrayList.remove(Rand.randomInRange(colArrayList.size()));
    }

    public void H_RemoveInEnd() {
        colArrayList.remove(colArrayList.size() - 1);
    }
}

class LinkedListOperations {
    public static LinkedList<Integer> colLinkedList = new LinkedList<>();
    private Singletone s;

    public void A_FillCollection() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElements; i++) {
            colLinkedList.add(Rand.randomInt());
        }
        System.out.println("1-1: size " + colLinkedList.size());
    }

    public void B_AddInBeginning() {
        colLinkedList.addFirst(Rand.randomInt());
    }

    public void C_AddInMiddle() {
        colLinkedList.add(Rand.randomInRange(colLinkedList.size()), Rand.randomInt());

    }

    public void D_AddInEnd() {
        colLinkedList.addLast(Rand.randomInt());
    }

    public void E_Search() {

        Integer searchEL = Rand.randomInt();
        for (Integer val : colLinkedList) {
            if (searchEL.equals(val)) {
                break;
            }
        }

    }

    public void F_RemoveFromBegining() {
        colLinkedList.removeFirst();
    }

    public void G_RemoveInMiddle() {
        colLinkedList.remove(Rand.randomInRange(colLinkedList.size()));
    }

    public void H_RemoveInEnd() {
        colLinkedList.removeLast();
    }
}

class CopyOnWriteArrayListOperations {
    public static CopyOnWriteArrayList
            colCopyOnWriteArrayListOperations =
            new CopyOnWriteArrayList();
    private Singletone s;

    public void A_FillCollection() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElements; i++) {
            colCopyOnWriteArrayListOperations.add(Rand.randomInt());

        }
        System.out.println("2-1: size " + colCopyOnWriteArrayListOperations.size());
    }

    public void B_AddInBeginning() {
        colCopyOnWriteArrayListOperations.add(0, Rand.randomInt());
    }

    public void C_AddInMiddle() {
        colCopyOnWriteArrayListOperations.add(Rand.randomInRange(colCopyOnWriteArrayListOperations.size()), Rand.randomInt());

    }

    public void D_AddInEnd() {
        colCopyOnWriteArrayListOperations.add(Rand.randomInt());
    }

    public void E_Search() {

        Integer searchEL = Rand.randomInt();

        for (Object val : colCopyOnWriteArrayListOperations) {
            if (searchEL.equals(val)) {
                break;
            }
        }

    }

    public void F_RemoveFromBegining() {
        colCopyOnWriteArrayListOperations.remove(0);
    }

    public void G_RemoveInMiddle() {
        colCopyOnWriteArrayListOperations.remove(Rand.randomInRange(colCopyOnWriteArrayListOperations.size()));
    }

    public void H_RemoveInEnd() {
        colCopyOnWriteArrayListOperations.remove(colCopyOnWriteArrayListOperations.size() - 1);
    }
}


public class Collections extends Fragment {

    final int startProcess = 0;
    final int endProcess = 1;
    @BindViews({R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6, R.id.time7, R.id.time8, R.id.time9, R.id.time10, R.id.time11,
            R.id.time12, R.id.time13, R.id.time14, R.id.time15, R.id.time16, R.id.time17, R.id.time18, R.id.time19, R.id.time20, R.id.time21, R.id.time22, R.id.time23, R.id.time24})
    List<TextView> tvList;
    @BindViews({R.id.pb1, R.id.pb2, R.id.pb3, R.id.pb4, R.id.pb5, R.id.pb6, R.id.pb7, R.id.pb8, R.id.pb9, R.id.pb10, R.id.pb11, R.id.pb12, R.id.pb13, R.id.pb14, R.id.pb15,
            R.id.pb16, R.id.pb17, R.id.pb18, R.id.pb19, R.id.pb20, R.id.pb21, R.id.pb22, R.id.pb23, R.id.pb24})
    List<ProgressBar> pbList;
    @BindView(R.id.numCol)
    EditText num;
    private Handler mHandler;
    private Singletone s;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);

        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case startProcess:
                        System.out.println("Старт " + msg.arg1 + " " + msg.arg2);
                        pbList.get((msg.arg1 + 1) + (msg.arg2 * 8) - 1).setVisibility(getView().VISIBLE);
                        break;
                    case endProcess:
                        pbList.get((msg.arg1 + 1) + (msg.arg2 * 8) - 1).setVisibility(getView().GONE);
                        tvList.get((msg.arg1 + 1) + (msg.arg2 * 8) - 1).setText(msg.obj + "ms");
                        break;
                }
            }
        };

        return view;
    }

    @OnClick(R.id.testCol)
    public void butclick() {
        s = Singletone.getInstance();
        s.numElements = Integer.parseInt(num.getText().toString());
        for (TextView tv : tvList) {
            tv.setText("");
        }

        ArraylistOperations arraylistOperations = new ArraylistOperations();
        LinkedListOperations linkedListOperations = new LinkedListOperations();
        CopyOnWriteArrayListOperations
                copyOnWriteArrayListOperations =
                new CopyOnWriteArrayListOperations();

        Method[] operations0 = ArraylistOperations.class.getDeclaredMethods();
        Method[] operations1 = LinkedListOperations.class.getDeclaredMethods();
        Method[] operations2 = CopyOnWriteArrayListOperations.class.getDeclaredMethods();

        ExecutorService
                executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

        List<myCallableTask> tasks = new ArrayList<>();
        for (int it = 0; it <= 7; it++) {
            for (int collections = 0; collections <= 2; collections++) {
                tasks.add(new myCallableTask(operations0[it], operations1[it], operations2[it], arraylistOperations, linkedListOperations, copyOnWriteArrayListOperations, it, collections, mHandler));
            }
        }


        for (myCallableTask task : tasks) {
            //try {
            //     TimeUnit.MICROSECONDS.sleep(1000);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            executorService.submit(task);
        }
        executorService.shutdown();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    class myCallableTask implements Callable<Integer> {

        private final int it;
        private final int collections;
        private final Method m0;
        private final Method m1;
        private final Method m2;
        private final ArraylistOperations arraylistOperations;
        private final LinkedListOperations linkedListOperations;
        private final CopyOnWriteArrayListOperations copyOnWriteArrayListOperations;
        private final Handler mHandler;
        private int time;
        private long startTime;


        public myCallableTask(Method m0, Method m1, Method m2, ArraylistOperations arraylistOperations, LinkedListOperations linkedListOperations, CopyOnWriteArrayListOperations copyOnWriteArrayListOperations, int it, int collections, Handler mHandler) {
            this.collections = collections;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.m2 = m2;
            this.arraylistOperations = arraylistOperations;
            this.linkedListOperations = linkedListOperations;
            this.copyOnWriteArrayListOperations = copyOnWriteArrayListOperations;
            this.mHandler = mHandler;

        }

        @Override
        public Integer call() throws Exception {

            try {
                Message msg =
                        mHandler.obtainMessage(startProcess, it, collections, 0);
                mHandler.sendMessage(msg);


                switch (collections) {
                    case (0):
                        synchronized (arraylistOperations) {
                            startTime = System.currentTimeMillis();
                            m0.invoke(arraylistOperations);
                            break;
                        }

                    case (1):
                        synchronized (linkedListOperations) {
                            startTime = System.currentTimeMillis();
                            m1.invoke(linkedListOperations);
                            break;
                        }

                    case (2):
                        synchronized (copyOnWriteArrayListOperations) {
                            startTime = System.currentTimeMillis();
                            m2.invoke(copyOnWriteArrayListOperations);
                        }

                }

                long duration = System.currentTimeMillis() - startTime;
                time = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                msg =
                        mHandler.obtainMessage(endProcess, it, collections, time);
                mHandler.sendMessage(msg);


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
