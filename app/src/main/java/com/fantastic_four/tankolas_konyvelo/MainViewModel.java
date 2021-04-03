package com.fantastic_four.tankolas_konyvelo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> buttonId = new MutableLiveData<>();

    public void setClickedButtonId(int id){
        buttonId.setValue(id);
    }

    public LiveData<Integer> getClickedButton(){
        return buttonId;
    }

}
