package com.newsong.newsongtime.ui.serving;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class servingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public servingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is serving fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}