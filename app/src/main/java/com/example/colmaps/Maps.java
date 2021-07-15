package com.example.colmaps;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


class FillingMaps {
    public static HashMap<Integer, Integer> hashMap = new HashMap<>();
    public static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    private Singletone s;

    public void A_FillHashMap() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElementsMap; i++) {
            hashMap.put(i, Rand.randomInt());
        }
    }

    public void B_fillTreeMap() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElementsMap; i++) {
            treeMap.put(i, Rand.randomInt());
        }
    }
}

class HashMapOperations {

    public void B_addElement(HashMap<Integer, Integer> copyMap) {
        copyMap.put(Rand.randomInt(), Rand.randomInt());
    }

    public void C_searchElement(HashMap<Integer, Integer> copyMap) {

        copyMap.containsKey(Rand.randomInRange(copyMap.size()));
    }

    public void D_removeElement(HashMap<Integer, Integer> copyMap) {
        copyMap.remove(Rand.randomInRange(copyMap.size()));
    }
}

class TreeMapOperations {

    private Singletone s;


    public void B_addElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.put(Rand.randomInt(), Rand.randomInt());
    }

    public void C_searchElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.containsKey(Rand.randomInRange(copyMap.size()));
    }

    public void D_removeElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.remove(Rand.randomInRange(copyMap.size()));
    }
}

public class Maps extends Fragment {

    private int pageNumber;
    public static Maps newInstance(int page) {
        Maps fragment = new Maps();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }



    final int startProcess = 0;
    final int endProcess = 1;
    @BindViews({R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6, R.id.time7, R.id.time8})
    List<TextView> tvList;
    @BindViews({R.id.pb1, R.id.pb2, R.id.pb3, R.id.pb4, R.id.pb5, R.id.pb6, R.id.pb7, R.id.pb8})
    List<ProgressBar> pbList;
    @BindView(R.id.numMaps)
    EditText numElements;
    @BindView(R.id.testMap)
    Button butTest;
    private Handler mHandler;
    private Singletone s;
    private Unbinder unbinder;
    private String [] result = new String[8];
    private ExecutorService executorService;
    private boolean butFree;


    @Override
    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.v("MyApp","on create");
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 2;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps, container, false);
        butFree=true;
        unbinder = ButterKnife.bind(this, view);
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case startProcess:
                        pbList.get(msg.arg1).setVisibility(View.VISIBLE);
                        break;
                    case endProcess:
                        pbList.get(msg.arg1).setVisibility(View.GONE);
                        tvList.get(msg.arg1).setText(msg.arg2 + getResources().getString(R.string.ms));
                        tvList.get(msg.arg1).setVisibility(View.VISIBLE);

                        if (executorService.isTerminated()){
                            butTest.setEnabled(true);
                            butFree=true;
                        }
                        break;
                }
            }
        };

        if (!(savedInstanceState == null)) {
            boolean setButStatus=savedInstanceState.getBoolean("butFree");
            butTest.setEnabled(setButStatus);
            String[] timeResult = savedInstanceState.getStringArray("res");

            for (int i = 0; i <= tvList.size() - 1; i++) {
                if (timeResult[i]!=null) {
                    tvList.get(i).setText(timeResult[i] + getResources().getString(R.string.ms));
                    tvList.get(i).setVisibility(View.VISIBLE);
                }
            }
        }
        return view;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("res", result);
        outState.putBoolean("butFree", butFree);
    }

    @OnClick(R.id.testMap)
    public void butClick() {
        s = Singletone.getInstance();
        butTest.setEnabled(false);
        butFree=false;

        result=new String[8];

        if (numElements.getText().toString().isEmpty()){
            numElements.setHint(R.string.warning);
            return;
        }
        s.numElementsMap = Integer.parseInt(numElements.getText().toString());
        for (TextView tv : tvList) {
            tv.setText(R.string.empty);
        }


        FillingMaps fillingMaps = new FillingMaps();
        HashMapOperations hashMapOperations = new HashMapOperations();
        TreeMapOperations treeMapOperations = new TreeMapOperations();


        Method[] fillMaps = FillingMaps.class.getDeclaredMethods();
        Method[] operations0 = HashMapOperations.class.getDeclaredMethods();
        Method[] operations1 = TreeMapOperations.class.getDeclaredMethods();

        int numThreads = Runtime.getRuntime().availableProcessors() - 1;
        executorService = Executors.newFixedThreadPool(numThreads);
        List<MyCallableTask> tasks = new ArrayList<>();

        for (int fillMapIt = 0; fillMapIt <= 1; fillMapIt++) {
            tasks.add(new MyCallableTask(fillMaps[fillMapIt], null, null, fillingMaps, null,
                    null, 0, fillMapIt, mHandler));
        }

        for (int it = 1; it <= 3; it++) {
            for (int maps = 0; maps <= 1; maps++) {
                tasks.add(new MyCallableTask(null, operations0[it - 1], operations1[it - 1], null, hashMapOperations,
                        treeMapOperations, it, maps, mHandler));

            }
        }
        for (MyCallableTask task : tasks) {
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

    class MyCallableTask implements Callable<Integer> {

        private final int it;
        private final int maps;
        private final Method m0;
        private final Method m1;
        private final Method method;
        private final FillingMaps fillingMaps;
        private final HashMapOperations hashMapOperations;
        private final TreeMapOperations treeMapOperations;
        private final Handler mHandler;
        private long startTime;
        private int time;
        private int index;

        public MyCallableTask(Method method, Method m0, Method m1, FillingMaps fillingMaps,
                              HashMapOperations hashMapOperations, TreeMapOperations treeMapOperations,
                              int it, int maps, Handler mHandler) {
            this.maps = maps;
            this.it = it;
            this.m0 = m0;
            this.m1 = m1;
            this.method = method;
            this.fillingMaps = fillingMaps;
            this.hashMapOperations = hashMapOperations;
            this.treeMapOperations = treeMapOperations;
            this.mHandler = mHandler;
        }

        @Override
        public Integer call() {
            try {
                index=(it + 1) + (maps * 4) - 1;
                Message msg =
                        mHandler.obtainMessage(startProcess, index, 0);
                mHandler.sendMessage(msg);

                if (it == 0) {
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingMaps);
                } else {
                    switch (maps) {
                        case (0):
                            while (FillingMaps.hashMap.size() < s.numElementsMap) {
                            }
                            HashMap<Integer, Integer> copyMap0 = new HashMap<>(FillingMaps.hashMap);
                            startTime = System.currentTimeMillis();
                            m0.invoke(hashMapOperations, copyMap0);
                            break;
                        case (1):
                            while (FillingMaps.treeMap.size() < s.numElementsMap) {
                            }
                            TreeMap<Integer, Integer> copyMap1 = new TreeMap<>(FillingMaps.treeMap);
                            startTime = System.currentTimeMillis();
                            m1.invoke(treeMapOperations, copyMap1);
                            break;
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                time = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                msg = mHandler.obtainMessage(endProcess, index, time);
                mHandler.sendMessage(msg);
                result[Integer.parseInt(String.valueOf(index))] = String.valueOf(time);
            } catch (IllegalAccessException | InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}






