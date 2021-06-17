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
    private static final Random element = new Random();

    public static int randomInRange(int numEl) {
        int randomEl = (int) (Math.random() * numEl);
        return randomEl;
    }

    public static Integer randomInt() {
        int newEl = element.nextInt();
        return newEl;
    }
}

class FillingCollections {
    public static ArrayList<Integer> colArrayList = new ArrayList<>();
    public static LinkedList<Integer> colLinkedList = new LinkedList<>();
    public static CopyOnWriteArrayList <Integer> colCopyOnWriteArrayList =
            new CopyOnWriteArrayList<>();


    private Singletone s;

    public void A_FillArrayList() {
        s = Singletone.getInstance();
        colArrayList.clear();
        for (int i = 0; i < s.numElements; i++) {
            colArrayList.add(Rand.randomInt());
        }


    }

    public void B_FillLinkedList() {
        s = Singletone.getInstance();
        colLinkedList.clear();
        for (int i = 0; i < s.numElements; i++) {
            colLinkedList.add(Rand.randomInt());
        }
    }

    public void C_FillCopyOnWriteArrayList() {
        s = Singletone.getInstance();
        colCopyOnWriteArrayList.clear();
        Integer[] copy = new Integer[s.numElements];
        for (int i = 0; i < s.numElements; i++) {
            copy[i] = Rand.randomInt();
        }
        colCopyOnWriteArrayList = new CopyOnWriteArrayList<>(copy);
    }
}

class ArraylistOperations {

    public void B_AddInBeginning(ArrayList<Integer> copyCol) {
        copyCol.add(0, Rand.randomInt());
    }

    public void C_AddInMiddle(ArrayList<Integer> copyCol) {
        copyCol.add(Rand.randomInRange(copyCol.size()), Rand.randomInt());
    }

    public void D_AddInEnd(ArrayList<Integer> copyCol) {
        copyCol.add(Rand.randomInt());
    }

    public void E_Search(ArrayList<Integer> copyCol) {
        Integer searchEL = Rand.randomInt();
        for (Integer val : copyCol) {
            if (searchEL.equals(val)) {
                break;
            }
        }
    }

    public void F_RemoveFromBegining(ArrayList<Integer> copyCol) {
        copyCol.remove(0);
    }

    public void G_RemoveInMiddle(ArrayList<Integer> copyCol) {
        copyCol.remove(Rand.randomInRange(copyCol.size()));
    }

    public void H_RemoveInEnd(ArrayList<Integer> copyCol) {
        copyCol.remove(copyCol.size() - 1);
    }
}

class LinkedListOperations {

    public void B_AddInBeginning(LinkedList<Integer> copyCol) {
        copyCol.addFirst(Rand.randomInt());
    }

    public void C_AddInMiddle(LinkedList<Integer> copyCol) {
        copyCol.add(Rand.randomInRange(copyCol.size()), Rand.randomInt());
    }

    public void D_AddInEnd(LinkedList<Integer> copyCol) {
        copyCol.addLast(Rand.randomInt());
    }

    public void E_Search(LinkedList<Integer> copyCol) {
        Integer searchEL = Rand.randomInt();
        for (Integer val : copyCol) {
            if (searchEL.equals(val)) {
                break;
            }
        }
    }

    public void F_RemoveFromBegining(LinkedList<Integer> copyCol) {
        copyCol.removeFirst();
    }

    public void G_RemoveInMiddle(LinkedList<Integer> copyCol) {
        copyCol.remove(Rand.randomInRange(copyCol.size()));
    }
    public void H_RemoveInEnd(LinkedList<Integer> copyCol) {
        copyCol.removeLast();
    }
}

class CopyOnWriteArrayListOperations {

    public void B_AddInBeginning(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.add(0, Rand.randomInt());
    }

    public void C_AddInMiddle(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.add(Rand.randomInRange(copyCol.size()), Rand.randomInt());

    }

    public void D_AddInEnd(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.add(Rand.randomInt());
    }

    public void E_Search(CopyOnWriteArrayList<Integer> copyCol) {

        Integer searchEL = Rand.randomInt();

        for (Integer val : copyCol) {
            if (searchEL.equals(val)) {
                break;
            }
        }
    }

    public void F_RemoveFromBegining(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.remove(0);
    }

    public void G_RemoveInMiddle(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.remove(Rand.randomInRange(copyCol.size()));
    }

    public void H_RemoveInEnd(CopyOnWriteArrayList<Integer> copyCol) {
        copyCol.remove(copyCol.size() - 1);
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
        View view = inflater.inflate(R.layout.collections, container, false);
        unbinder = ButterKnife.bind(this, view);
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case startProcess:
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

        FillingCollections fillingCollections = new FillingCollections();
        ArraylistOperations arraylistOperations = new ArraylistOperations();
        LinkedListOperations linkedListOperations = new LinkedListOperations();
        CopyOnWriteArrayListOperations
                copyOnWriteArrayListOperations =
                new CopyOnWriteArrayListOperations();

        Method[] fillCol = FillingCollections.class.getDeclaredMethods();
        Method[] operations0 = ArraylistOperations.class.getDeclaredMethods();
        Method[] operations1 = LinkedListOperations.class.getDeclaredMethods();
        Method[] operations2 = CopyOnWriteArrayListOperations.class.getDeclaredMethods();

        int numThreads = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService
                executorService =
                Executors.newFixedThreadPool(numThreads);
        List<myCallableTask> tasks = new ArrayList<>();

        for (int fillColIt = 0; fillColIt <= 2; fillColIt++) {
            tasks.add(new myCallableTask(fillCol[fillColIt], null, null, null, fillingCollections, null,
                    null, null, 0, fillColIt, mHandler));
        }

        for (int it = 1; it <= 7; it++) {
            for (int collections = 0; collections <= 2; collections++) {
                tasks.add(new myCallableTask(null, operations0[it - 1], operations1[it - 1], operations2[it - 1], null, arraylistOperations,
                        linkedListOperations, copyOnWriteArrayListOperations, it, collections, mHandler));

            }
        }
        for (myCallableTask task : tasks) {
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
        private final Method method;
        private final FillingCollections fillingCollections;
        private final ArraylistOperations arraylistOperations;
        private final LinkedListOperations linkedListOperations;
        private final CopyOnWriteArrayListOperations copyOnWriteArrayListOperations;
        private final Handler mHandler;
        private long startTime;
        private int time;

        public myCallableTask(Method method, Method m0, Method m1, Method m2, FillingCollections fillingCollections,
                              ArraylistOperations arraylistOperations, LinkedListOperations linkedListOperations,
                              CopyOnWriteArrayListOperations copyOnWriteArrayListOperations,
                              int it, int collections, Handler mHandler) {
            this.collections = collections;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.m2 = m2;
            this.method = method;
            this.arraylistOperations = arraylistOperations;
            this.linkedListOperations = linkedListOperations;
            this.copyOnWriteArrayListOperations = copyOnWriteArrayListOperations;
            this.mHandler = mHandler;
            this.fillingCollections = fillingCollections;
        }

        @Override
        public Integer call() {
            try {
                Message msg =
                        mHandler.obtainMessage(startProcess, it, collections, 0);
                mHandler.sendMessage(msg);

                if (it == 0) {
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingCollections);
                }
                else {
                    switch (collections) {
                        case (0):
                            while (FillingCollections.colArrayList.size() < s.numElements) {
                            }
                            ArrayList<Integer> copyCol0 =
                                    new ArrayList<>(FillingCollections.colArrayList);
                            startTime = System.currentTimeMillis();
                            m0.invoke(arraylistOperations, copyCol0);
                            break;
                        case (1):
                            while (FillingCollections.colLinkedList.size() < s.numElements) {
                            }
                            LinkedList<Integer> copyCol1 =
                                    new LinkedList<>(FillingCollections.colLinkedList);
                            startTime = System.currentTimeMillis();
                            m1.invoke(linkedListOperations, copyCol1);
                            break;
                        case (2):
                            while (FillingCollections.colCopyOnWriteArrayList.size() < s.numElements) {
                            }
                            CopyOnWriteArrayList<Integer>
                                    copyCol2 =new CopyOnWriteArrayList<>(FillingCollections.colCopyOnWriteArrayList);
                            startTime = System.currentTimeMillis();
                            m2.invoke(copyOnWriteArrayListOperations, copyCol2);
                            break;
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                time = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                msg = mHandler.obtainMessage(endProcess, it, collections, time);
                mHandler.sendMessage(msg);

            } catch (IllegalAccessException | InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
