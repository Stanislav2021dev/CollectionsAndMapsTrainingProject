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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

class Rand {
    public static int randomInRange(int numElements) {
        int randomEl = (int) (Math.random() * numElements);
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

public class Fragment1 extends Fragment {

    final int StartProcess = 0;
    final int EndProcess = 1;
    public Method[] operations;
    private Button test;
    private EditText num;
    private TextView time;
    private ProgressBar[][] myPb = null;
    private TextView[][] myTv = null;
    private Handler mHandler;
    private Singletone s;

    public Fragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        num = view.findViewById(R.id.numCol);
        myPb = new ProgressBar[][]
                {
                        {view.findViewById(R.id.pb1),
                                view.findViewById(R.id.pb2),
                                view.findViewById(R.id.pb3),
                                view.findViewById(R.id.pb4),
                                view.findViewById(R.id.pb5),
                                view.findViewById(R.id.pb6),
                                view.findViewById(R.id.pb7),
                                view.findViewById(R.id.pb8)},
                        {view.findViewById(R.id.pb9),
                                view.findViewById(R.id.pb10),
                                view.findViewById(R.id.pb11),
                                view.findViewById(R.id.pb12),
                                view.findViewById(R.id.pb13),
                                view.findViewById(R.id.pb14),
                                view.findViewById(R.id.pb15),
                                view.findViewById(R.id.pb16)},
                        {view.findViewById(R.id.pb17),
                                view.findViewById(R.id.pb18),
                                view.findViewById(R.id.pb19),
                                view.findViewById(R.id.pb20),
                                view.findViewById(R.id.pb21),
                                view.findViewById(R.id.pb22),
                                view.findViewById(R.id.pb23),
                                view.findViewById(R.id.pb24)}
                };
        myTv = new TextView[][]
                {
                        {view.findViewById(R.id.time1),
                                view.findViewById(R.id.time2),
                                view.findViewById(R.id.time3),
                                view.findViewById(R.id.time4),
                                view.findViewById(R.id.time5),
                                view.findViewById(R.id.time6),
                                view.findViewById(R.id.time7),
                                view.findViewById(R.id.time8)},
                        {view.findViewById(R.id.time9),
                                view.findViewById(R.id.time10),
                                view.findViewById(R.id.time11),
                                view.findViewById(R.id.time12),
                                view.findViewById(R.id.time13),
                                view.findViewById(R.id.time14),
                                view.findViewById(R.id.time15),
                                view.findViewById(R.id.time16)},
                        {view.findViewById(R.id.time17),
                                view.findViewById(R.id.time18),
                                view.findViewById(R.id.time19),
                                view.findViewById(R.id.time20),
                                view.findViewById(R.id.time21),
                                view.findViewById(R.id.time22),
                                view.findViewById(R.id.time23),
                                view.findViewById(R.id.time24)}
                };

        test = view.findViewById(R.id.testCol);

        mHandler = new Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case StartProcess:
                        myPb[msg.arg2][msg.arg1].setVisibility(getView().VISIBLE);
                        break;
                    case EndProcess:
                        myPb[msg.arg2][msg.arg1].setVisibility(getView().GONE);
                        myTv[msg.arg2][msg.arg1].setText("time: " + msg.obj);
                        break;

                }
            }
        };


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = Singletone.getInstance();
                s.numElements = Integer.parseInt(num.getText().toString());

                ArraylistOperations arraylistOperations = new ArraylistOperations();
                LinkedListOperations linkedListOperations = new LinkedListOperations();
                CopyOnWriteArrayListOperations
                        copyOnWriteArrayListOperations =
                        new CopyOnWriteArrayListOperations();
                Thread[] threads = new Thread[3];
                for (int numThread = 0; numThread <= 2; numThread++) {


                    int finalNumThread = numThread;
                    threads[numThread] = new Thread(new Runnable() {
                        Message msg;

                        @Override
                        public void run() {


                            switch (finalNumThread) {
                                case (0):
                                    operations = ArraylistOperations.class.getDeclaredMethods();
                                    break;
                                case (1):
                                    operations = LinkedListOperations.class.getDeclaredMethods();
                                    break;
                                case (2):
                                    operations =
                                            CopyOnWriteArrayListOperations.class.getDeclaredMethods();
                                    break;
                            }

                            int it = 0;
                            for (Method method : operations) {

                                try {

                                    msg =
                                            mHandler.obtainMessage(StartProcess, it, finalNumThread, 0);
                                    mHandler.sendMessage(msg);

                                    long startTime = System.currentTimeMillis();

                                    switch (finalNumThread) {
                                        case (0):
                                            method.invoke(arraylistOperations);
                                            break;
                                        case (1):
                                            method.invoke(linkedListOperations);
                                            break;
                                        case (2):
                                            method.invoke(copyOnWriteArrayListOperations);
                                    }

                                    long duration = System.currentTimeMillis() - startTime;
                                    int time = Integer.parseInt(String.valueOf(duration));
                                    TimeUnit.SECONDS.sleep(1);
                                    msg =
                                            mHandler.obtainMessage(EndProcess, it, finalNumThread, time);
                                    mHandler.sendMessage(msg);
                                    it++;

                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                    });
                    threads[finalNumThread].start();
                }
            }
        });

        return view;
    }
}
