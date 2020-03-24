package com.example.myokhttp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    static MutableLiveData<String[]> liveData;

    public static MutableLiveData<String[]> getLiveData() {
        if (liveData==null){
            liveData=new MutableLiveData<>();
        }
        return liveData;
    }

    public  void setLiveData(MutableLiveData<String[]> liveData) {
        if (liveData==null){
            liveData=new MutableLiveData<>();
        }
            this.liveData = liveData;
    }
}
