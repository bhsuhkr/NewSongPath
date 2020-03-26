package com.newsong.newsongtime.ui.sending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class sendingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public sendingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sending fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}