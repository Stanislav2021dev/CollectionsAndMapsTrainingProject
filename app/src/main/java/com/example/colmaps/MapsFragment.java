package com.example.colmaps;

import android.os.Bundle;
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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


class FillingMaps {
    public static HashMap<Integer, Integer> hashMap = new HashMap<>();
    public static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    public static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    public static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    private Singletone s;

    public void A_FillHashMap() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElementsMap; i++) {
            hashMap.put(i, GenerateRandomElement.randomInt());
        }
        countDownLatch1.countDown();
    }

    public void B_fillTreeMap() {
        s = Singletone.getInstance();
        for (int i = 0; i < s.numElementsMap; i++) {
            treeMap.put(i, GenerateRandomElement.randomInt());
        }
        countDownLatch2.countDown();
    }
}

class OperationsWithHashMap {

    public void B_addElement(HashMap<Integer, Integer> copyMap) {
        copyMap.put(GenerateRandomElement.randomInt(), GenerateRandomElement.randomInt());
    }

    public void C_searchElement(HashMap<Integer, Integer> copyMap) {

        copyMap.containsKey(GenerateRandomElement.randomInRange(copyMap.size()));
    }

    public void D_removeElement(HashMap<Integer, Integer> copyMap) {
        copyMap.remove(GenerateRandomElement.randomInRange(copyMap.size()));
    }
}

class OperationsWithTreeMap {

    public void B_addElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.put(GenerateRandomElement.randomInt(), GenerateRandomElement.randomInt());
    }

    public void C_searchElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.containsKey(GenerateRandomElement.randomInRange(copyMap.size()));
    }

    public void D_removeElement(TreeMap<Integer, Integer> copyMap) {
        copyMap.remove(GenerateRandomElement.randomInRange(copyMap.size()));
    }
}

public class MapsFragment extends Fragment {

    public static MapsViewModel mapModel;
    @BindViews({R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6, R.id.time7, R.id.time8})
    List<TextView> tvList;
    @BindViews({R.id.pb1, R.id.pb2, R.id.pb3, R.id.pb4, R.id.pb5, R.id.pb6, R.id.pb7, R.id.pb8})
    List<ProgressBar> pbList;
    @BindView(R.id.numMaps)
    EditText numElements;
    @BindView(R.id.testMap)
    Button butTest;
    private int pageNumber;
    private Singletone s;
    private Unbinder unbinder;
    private ExecutorService executorService;

    public static MapsFragment newInstance(int page) {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        mapsFragment.setArguments(args);
        return mapsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MyApp", "on create");
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps, container, false);
        unbinder = ButterKnife.bind(this, view);
        s = Singletone.getInstance();
        mapModel = new ViewModelProvider(this).get(MapsViewModel.class);

        MutableLiveData<Boolean[]> liveDataPbStatus = mapModel.getStatus();
        MutableLiveData<String[]> liveDataTimeResult = mapModel.getRes();
        MutableLiveData<Boolean> liveDataButStatus = mapModel.getButStatus();

        if (liveDataPbStatus == null) {
            liveDataPbStatus.postValue(MapsViewModel.getCurrentStatus());
        }

        liveDataPbStatus.observeForever(new Observer<Boolean[]>() {
            @Override
            public void onChanged(Boolean[] booleans) {
                if (pbList == null) {
                    unbinder = ButterKnife.bind(MapsFragment.this, view);
                } else {
                    for (int i = 0; i <= pbList.size() - 1; i++) {
                        if (liveDataPbStatus.getValue()[i] != null) {
                            if (liveDataPbStatus.getValue()[i]) {
                                pbList.get(i).setVisibility(View.VISIBLE);
                            } else {
                                pbList.get(i).setVisibility(View.GONE);
                            }
                        }

                    }
                }
            }
        });

        if (liveDataTimeResult == null) {
            liveDataTimeResult.postValue(MapsViewModel.getCurrentTime());
        }


        liveDataTimeResult.observeForever(new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {

                if (tvList == null) {
                    unbinder = ButterKnife.bind(MapsFragment.this, view);
                } else {
                    for (int i = 0; i <= tvList.size() - 1; i++) {
                        if (liveDataTimeResult.getValue()[i] != null) {
                            tvList.get(i).setText(liveDataTimeResult.getValue()[i] + " ms");
                            tvList.get(i).setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });


        liveDataButStatus.observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean booleans) {
                if (butTest == null) {
                    unbinder = ButterKnife.bind(MapsFragment.this, view);
                } else {
                    butTest.setEnabled(booleans);
                }
            }
        });
        return view;
    }

    @OnClick(R.id.testMap)
    public void butClick() {
        s = Singletone.getInstance();
        if (numElements.getText().toString().isEmpty()) {
            numElements.setHint(R.string.warning);
            return;
        }

        MapsViewModel.nullifyTimeResult();
        MapsViewModel.nullifyPbStatus();

        s.numElementsMap = Integer.parseInt(numElements.getText().toString());
        for (TextView tv : tvList) {
            tv.setVisibility(View.GONE);
        }

        FillingMaps fillingMaps = new FillingMaps();
        OperationsWithHashMap operationsWithHashMap = new OperationsWithHashMap();
        OperationsWithTreeMap operationsWithTreeMap = new OperationsWithTreeMap();

        Method[] fillMaps = FillingMaps.class.getDeclaredMethods();
        Method[] operations0 = OperationsWithHashMap.class.getDeclaredMethods();
        Method[] operations1 = OperationsWithTreeMap.class.getDeclaredMethods();

        int numThreads = Runtime.getRuntime().availableProcessors() - 1;
        executorService = Executors.newFixedThreadPool(numThreads);
        List<MapsViewModel.MyCallableTask> tasks = new ArrayList<>();

        for (int fillMapIt = 0; fillMapIt <= 1; fillMapIt++) {
            tasks.add(new MapsViewModel.MyCallableTask(fillMaps[fillMapIt], null, null, fillingMaps, null,
                    null, 0, fillMapIt));
        }

        for (int it = 1; it <= 3; it++) {
            for (int maps = 0; maps <= 1; maps++) {
                tasks.add(new MapsViewModel.MyCallableTask(null, operations0[it - 1], operations1[it - 1], null, operationsWithHashMap,
                        operationsWithTreeMap, it, maps));

            }
        }
        for (MapsViewModel.MyCallableTask task : tasks) {
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
}






