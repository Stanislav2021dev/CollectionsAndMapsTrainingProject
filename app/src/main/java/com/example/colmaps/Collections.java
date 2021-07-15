package com.example.colmaps;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
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
    public static CopyOnWriteArrayList<Integer> colCopyOnWriteArrayList =
            new CopyOnWriteArrayList<>();
    private Singletone s;
    public static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    public static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    public static CountDownLatch countDownLatch3 = new CountDownLatch(1);

    public void A_FillArrayList() {
        s = Singletone.getInstance();
        colArrayList.clear();
        for (int i = 0; i < s.numElementsCollection; i++) {
            colArrayList.add(Rand.randomInt());
        }
        countDownLatch1.countDown();
    }

    public void B_FillLinkedList() {
        s = Singletone.getInstance();
        colLinkedList.clear();
        for (int i = 0; i < s.numElementsCollection; i++) {
            colLinkedList.add(Rand.randomInt());
        }
        countDownLatch2.countDown();

    }

    public void C_FillCopyOnWriteArrayList() {
        s = Singletone.getInstance();
        colCopyOnWriteArrayList.clear();
        Integer[] copy = new Integer[s.numElementsCollection];
        for (int i = 0; i < s.numElementsCollection; i++) {
            copy[i] = Rand.randomInt();
        }
        colCopyOnWriteArrayList = new CopyOnWriteArrayList<>(copy);
        countDownLatch3.countDown();
    }
}

class ArrayListOperations {

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

    private int pageNumber;
    public static Collections newInstance(int page) {
        Collections fragment = new Collections();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    @BindViews({R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6, R.id.time7, R.id.time8, R.id.time9, R.id.time10, R.id.time11,
            R.id.time12, R.id.time13, R.id.time14, R.id.time15, R.id.time16, R.id.time17, R.id.time18, R.id.time19, R.id.time20, R.id.time21, R.id.time22, R.id.time23, R.id.time24})
    List<TextView> tvList;
    @BindViews({R.id.pb1, R.id.pb2, R.id.pb3, R.id.pb4, R.id.pb5, R.id.pb6, R.id.pb7, R.id.pb8, R.id.pb9, R.id.pb10, R.id.pb11, R.id.pb12, R.id.pb13, R.id.pb14, R.id.pb15,
            R.id.pb16, R.id.pb17, R.id.pb18, R.id.pb19, R.id.pb20, R.id.pb21, R.id.pb22, R.id.pb23, R.id.pb24})
    List<ProgressBar> pbList;

    @BindView(R.id.numCol)
    EditText numElements;
    @BindView(R.id.testCol)
    Button butTest;
    private Singletone s;
    private Unbinder unbinder;
    public static CollectionsViewModel colModel;
    private String result[];
    private Boolean butFree;


    @Override
    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.v("MyApp","on create");
            pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.collections, container, false);
        unbinder = ButterKnife.bind(this, view);
        s = Singletone.getInstance();

        colModel= new ViewModelProvider(this).get(CollectionsViewModel.class);

        MutableLiveData<Boolean[]> liveDataPbStatus=colModel.getStatus();
        MutableLiveData<String[]> liveDataTimeResult=colModel.getRes();

        if (liveDataPbStatus==null){
            liveDataPbStatus.postValue(CollectionsViewModel.getCurrentStatus());
        }

        liveDataPbStatus.observeForever(new Observer<Boolean[]>() {
            @Override
            public void onChanged(Boolean[] booleans) {
                if (pbList==null) {
                    unbinder = ButterKnife.bind(Collections.this, view);
                }
                else {
                    for (int i = 0; i <= pbList.size() - 1; i++) {
                        if (liveDataPbStatus.getValue()[i]!=null){
                            if (liveDataPbStatus.getValue()[i]) {
                                pbList.get(i).setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                pbList.get(i).setVisibility(View.GONE);
                            }
                        }

                    }
                }
            }
        });

        if (liveDataTimeResult==null){
            liveDataTimeResult.postValue(CollectionsViewModel.getCurrentTime());
        }


        liveDataTimeResult.observeForever(new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {

                if (tvList==null) {
                  unbinder = ButterKnife.bind(Collections.this, view);
                }
                else {
                        for (int i = 0; i <= tvList.size() - 1; i++) {
                            if (liveDataTimeResult.getValue()[i] != null) {
                               tvList.get(i).setText(liveDataTimeResult.getValue()[i] + "ms");
                             //  tvList.get(i).setText(liveDataTimeResult.getValue()[i] + getResources().getString(R.string.ms));
                               tvList.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                }
                if (!(Arrays.asList(liveDataTimeResult.getValue()).contains(null)) &&butTest!=null){
                    butTest.setEnabled(true);
                    s.butFree = true;
                }
            }
        });
        return view;
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.v("MyApp","on start ");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (s.butFree && butTest!=null) {
            butTest.setEnabled(true);
        }
        else butTest.setEnabled(false);
        Log.v("MyApp","on resume ");
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.v("MyApp","on pause ");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.v("MyApp","on stop ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("MyApp", "On destroy");
        if (unbinder != null) {
            unbinder.unbind();
        }
    }



    @OnClick(R.id.testCol)
    public void butclick() {

        if (numElements.getText().toString().isEmpty()){
            numElements.setHint(R.string.warning);
            return;
        }

        butTest.setEnabled(false);
        s.butFree=false;

        CollectionsViewModel.nullifyTimeResult();
        CollectionsViewModel.nullifyPbStatus();

        s.numElementsCollection = Integer.parseInt(numElements.getText().toString());
        for (TextView tv : tvList) {
            tv.setVisibility(View.GONE);
        }

        FillingCollections fillingCollections = new FillingCollections();
        ArrayListOperations arraylistOperations = new ArrayListOperations();
        LinkedListOperations linkedListOperations = new LinkedListOperations();
        CopyOnWriteArrayListOperations
                copyOnWriteArrayListOperations =
                new CopyOnWriteArrayListOperations();

        Method[] fillCol = FillingCollections.class.getDeclaredMethods();
        Method[] operations0 = ArrayListOperations.class.getDeclaredMethods();
        Method[] operations1 = LinkedListOperations.class.getDeclaredMethods();
        Method[] operations2 = CopyOnWriteArrayListOperations.class.getDeclaredMethods();

        int numThreads = Runtime.getRuntime().availableProcessors() - 1;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<CollectionsViewModel.MyCallableTask> tasks = new ArrayList<>();

        for (int fillColIt = 0; fillColIt <= 2; fillColIt++) {
            tasks.add(new CollectionsViewModel.MyCallableTask(fillCol[fillColIt], null, null, null, fillingCollections, null,
                    null, null, 0, fillColIt));
        }

        for (int it = 1; it <= 7; it++) {
            for (int collections = 0; collections <= 2; collections++) {
                tasks.add(new CollectionsViewModel.MyCallableTask(null, operations0[it - 1], operations1[it - 1], operations2[it - 1], null, arraylistOperations,
                        linkedListOperations, copyOnWriteArrayListOperations, it, collections));
            }
        }
        for (CollectionsViewModel.MyCallableTask task : tasks) {
                executorService.submit(task);
        }
        executorService.shutdown();
    }
}

