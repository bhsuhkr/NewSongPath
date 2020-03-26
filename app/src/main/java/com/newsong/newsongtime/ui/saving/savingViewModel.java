package com.newsong.newsongtime.ui.saving;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class savingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public savingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is saving fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}