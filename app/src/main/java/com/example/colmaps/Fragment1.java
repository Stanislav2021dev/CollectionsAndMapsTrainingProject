package com.example.colmaps;


import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Random;

public class Fragment1 extends Fragment {

    public static ArrayList<Integer> Collection = new ArrayList<>();
    private int numElements;
    private Button test;
    private EditText num;
    private int progress;
    private TextView time;
    private ProgressBar[] myPb = null;
    private TextView[] myTv = null;

    public Fragment1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        num = view.findViewById(R.id.numCol);

        myPb = new ProgressBar[]
                {
                        view.findViewById(R.id.pb1),
                        view.findViewById(R.id.pb2),
                        view.findViewById(R.id.pb3),
                        view.findViewById(R.id.pb4),
                        view.findViewById(R.id.pb5),
                        view.findViewById(R.id.pb6),
                        view.findViewById(R.id.pb7),
                        view.findViewById(R.id.pb8),

                };
        myTv = new TextView[]
                {
                        view.findViewById(R.id.time1),
                        view.findViewById(R.id.time2),
                        view.findViewById(R.id.time3),
                        view.findViewById(R.id.time4),
                        view.findViewById(R.id.time5),
                        view.findViewById(R.id.time6),
                        view.findViewById(R.id.time7),
                        view.findViewById(R.id.time8),
                };

        test = view.findViewById(R.id.testCol);
        time = view.findViewById(R.id.time1);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Thread[] threads = new Thread[8];
                for (int i = 0; i <= 7; i++) {

                    myPb[i].setVisibility(getView().VISIBLE);
                    long startTime = System.currentTimeMillis();


                    int finalI = i;

                    threads[i] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            switch (finalI) {
                                case (0):
                                    FillCollection();
                                    break;
                                case (1):
                                    AddInBeginning();
                                    break;
                                case (2):
                                    AddInMiddle();
                                    break;
                                case (3):
                                    AddInEnd();
                                    break;
                                case (4):
                                    Search(Collection);
                                    break;
                                case (5):
                                    RemoveFromBegining();
                                    break;
                                case (6):
                                    RemoveInMiddle();
                                case (7):
                                    RemoveInEnd();
                                default:
                                    break;
                            }

                            myPb[finalI].post(new Runnable() {
                                @Override
                                public void run() {
                                    long duration = System.currentTimeMillis() - startTime;
                                    myPb[finalI].setVisibility(getView().GONE);
                                    myTv[finalI].setText(String.valueOf(duration));
                                }
                            });
                        }
                    });
                    threads[finalI].start();
                    if (finalI == 0) {
                        try {
                            threads[0].join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return view;
    }

    public Integer randomInt() {
        Random element = new Random();
        int newEl = element.nextInt();
        return newEl;
    }

    public Integer randomInRange() {
        int indexEl = (int) (Math.random() * numElements);
        return indexEl;
    }

    public void FillCollection() {
        numElements = Integer.parseInt(num.getText().toString());
        for (int i = 0; i < numElements; i++) {
            Collection.add(randomInt());
        }
    }

    public void AddInBeginning() {
        Collection.add(0, randomInt());
    }

    public void AddInMiddle() {
        Collection.add(randomInRange(), randomInt());
    }

    public void AddInEnd() {
        Collection.add(randomInt());
    }

    public void Search(ArrayList<Integer> Collection) {
        ArrayList<Integer> Col = new ArrayList<>(Collection);
        for (Integer val : Col) {
            if (randomInRange().equals(val)) {
                break;
            }
        }
    }

    public void RemoveFromBegining() {
        Collection.remove(0);
    }

    public void RemoveInMiddle() {
        Collection.remove(randomInRange());
    }

    public void RemoveInEnd() {
        Collection.remove(numElements - 1);
    }
}





