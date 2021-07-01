package com.example.colmaps;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class LiveDataTime extends MutableLiveData<String[]> {
    private Singletone s;
    public void setResult(String[] res){
        postValue(res);
    }
    @Override
    public void onActive(){
        super.onActive();
        Log.d("MyApp","Active ");
        s = Singletone.getInstance();
        setResult(s.result);
    }
    @Override
    public void onInactive(){
        super.onInactive();
        Log.d("MyApp","No Active");
    }
}
