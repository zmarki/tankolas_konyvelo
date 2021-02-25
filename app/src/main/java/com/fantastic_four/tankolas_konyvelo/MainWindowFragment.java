package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

interface MainWindowFragmentCallBack {
    void mainWindowButtonClicked(int buttonCode);
}

public class MainWindowFragment extends Fragment implements View.OnClickListener {

    private MainWindowFragmentCallBack mainWindowFragmentCallBack;

    public static final int ADD_NEW_FILLING_BTNCODE = 111;
    public static final int CHECK_STATISTICS_BTNCODE = 222;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_window, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView addNewFillingImg = view.findViewById(R.id.img_add_new_filling);
        TextView addNewFillingText = view.findViewById(R.id.text_add_new_filling);
        addNewFillingText.setOnClickListener(this);
        addNewFillingImg.setOnClickListener(this);
    }

    public void setMainWindowFragmentListener(MainWindowFragmentCallBack mainWindowFragmentCallBack) {
        this.mainWindowFragmentCallBack = mainWindowFragmentCallBack;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_add_new_filling || view.getId() == R.id.text_add_new_filling) {
            if (mainWindowFragmentCallBack != null) {
                mainWindowFragmentCallBack.mainWindowButtonClicked(ADD_NEW_FILLING_BTNCODE);
            }
        }
    }
}
