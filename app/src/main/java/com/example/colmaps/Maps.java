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
        s=Singletone.getInstance();
        for (int i = 0; i <s.numElementsMap ; i++) {
            hashMap.put(i, Rand.randomInt());
        }
    }
    public void B_fillTreeMap() {
        s=Singletone.getInstance();
        for (int i = 0; i <s.numElementsMap ; i++) {
            treeMap.put(i, Rand.randomInt());
        }
    }
}

class HashMapOperations {

    public void B_addElement(HashMap<Integer,Integer> copyMap) {
        copyMap.put(Rand.randomInt(),Rand.randomInt());
    }

    public void C_searchElement(HashMap<Integer,Integer> copyMap) {

        copyMap.containsKey(Rand.randomInRange(copyMap.size()));
    }

    public void D_removeElement(HashMap<Integer,Integer> copyMap) {
        copyMap.remove(Rand.randomInRange(copyMap.size()));
    }
}
class TreeMapOperations {

    private Singletone s;


    public void B_addElement(TreeMap<Integer,Integer> copyMap) {
        copyMap.put(Rand.randomInt(),Rand.randomInt());
    }

    public void C_searchElement(TreeMap<Integer,Integer> copyMap) {
        copyMap.containsKey(Rand.randomInRange(copyMap.size()));
    }

    public void D_removeElement(TreeMap<Integer,Integer> copyMap) {
        copyMap.remove(Rand.randomInRange(copyMap.size()));
    }
}

public class Maps extends Fragment {
    private Button test;
    final int startProcess = 0;
    final int endProcess = 1;
    private Handler mHandler;
    private Singletone s;
    private Unbinder unbinder;
    @BindViews({R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6, R.id.time7, R.id.time8})
    List<TextView> tvList;
    @BindViews({R.id.pb1, R.id.pb2, R.id.pb3, R.id.pb4, R.id.pb5, R.id.pb6, R.id.pb7, R.id.pb8})
    List<ProgressBar> pbList;
    @BindView(R.id.numMaps)
    EditText numMaps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps, container, false);

        unbinder = ButterKnife.bind(this, view);
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case startProcess:
                        pbList.get((msg.arg1 + 1) + (msg.arg2 * 4) - 1).setVisibility(getView().VISIBLE);
                        break;
                    case endProcess:
                        pbList.get((msg.arg1 + 1) + (msg.arg2 * 4) - 1).setVisibility(getView().GONE);
                        tvList.get((msg.arg1 + 1) + (msg.arg2 * 4) - 1).setText(msg.obj + "ms");
                        break;
                }
            }
        };

        return view;
    }

    @OnClick(R.id.testMap)
    public void butClick() {
        s = Singletone.getInstance();
        s.numElementsMap = Integer.parseInt(numMaps.getText().toString());
        for (TextView tv : tvList) {
            tv.setText("");
        }

        FillingMaps fillingMaps = new FillingMaps();
        HashMapOperations hashMapOperations = new HashMapOperations();
        TreeMapOperations treeMapOperations = new TreeMapOperations();


        Method[] fillMaps = FillingMaps.class.getDeclaredMethods();
        Method[] operations0 = HashMapOperations.class.getDeclaredMethods();
        Method[] operations1 = TreeMapOperations.class.getDeclaredMethods();

        int numThreads = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService
                executorService =
                Executors.newFixedThreadPool(numThreads);
        List<myCallableTask> tasks = new ArrayList<>();

        for (int fillMapIt = 0; fillMapIt <= 1; fillMapIt++) {
            tasks.add(new myCallableTask(fillMaps[fillMapIt], operations0[0], operations1[0], fillingMaps, hashMapOperations,
                    treeMapOperations, 0, fillMapIt, mHandler));
        }

        for (int it = 1; it <= 3; it++) {
            for (int maps = 0; maps <= 1; maps++) {
                tasks.add(new myCallableTask(fillMaps[0], operations0[it - 1], operations1[it - 1], fillingMaps, hashMapOperations,
                        treeMapOperations, it, maps, mHandler));

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

        public myCallableTask(Method method, Method m0, Method m1, FillingMaps fillingMaps,
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
                Message msg =
                        mHandler.obtainMessage(startProcess, it, maps, 0);
                mHandler.sendMessage(msg);

                if (it == 0) {
                    startTime = System.currentTimeMillis();
                    method.invoke(fillingMaps);
                } else {
                    switch (maps) {
                        case (0):
                            while (FillingMaps.hashMap.size() < s.numElementsMap) {
                            }
                            HashMap<Integer,Integer> copyMap0= new HashMap<>(FillingMaps.hashMap);
                            startTime = System.currentTimeMillis();
                            m0.invoke(hashMapOperations, copyMap0);
                            break;
                        case (1):
                            while (FillingMaps.treeMap.size() < s.numElementsMap) {
                            }
                            TreeMap<Integer,Integer> copyMap1=new TreeMap<>(FillingMaps.treeMap);
                            startTime = System.currentTimeMillis();
                            m1.invoke(treeMapOperations, copyMap1);
                            break;

                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                time = Integer.parseInt(String.valueOf(duration));
                TimeUnit.SECONDS.sleep(1);
                msg = mHandler.obtainMessage(endProcess, it, maps, time);
                mHandler.sendMessage(msg);
            } catch (IllegalAccessException | InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}






