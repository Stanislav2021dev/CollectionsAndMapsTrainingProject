package com.example.colmaps;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class LiveDataPbStatus extends MutableLiveData<boolean[]> {
    private Singletone s;
    public void setResult(boolean[] res){
        postValue(res);
    }
    @Override
    public void onActive(){
        super.onActive();
        Log.d("MyApp","Active ");
        s = Singletone.getInstance();
        setResult(s.status);
    }
    @Override
    public void onInactive(){
        super.onInactive();
        Log.d("MyApp","No Active");
    }
}

