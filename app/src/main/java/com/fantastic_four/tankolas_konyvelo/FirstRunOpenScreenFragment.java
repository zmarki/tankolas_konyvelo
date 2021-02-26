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

interface FirstRunOpenScreenFragmentCallback {
    void onFirstRunScreenButtonClicked(int id);
}

public class FirstRunOpenScreenFragment extends Fragment implements View.OnClickListener {

    public static final int REGISTER_NEW_CAR_ID = 11;
    public static final int LOAD_DATABASE_ID = 22;

    private FirstRunOpenScreenFragmentCallback firstRunOpenScreenFragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_run, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgAddNewCar = view.findViewById(R.id.img_first_run_register_new);
        TextView textAddNewCar = view.findViewById(R.id.text_first_run_register_new);
        imgAddNewCar.setOnClickListener(this);
        textAddNewCar.setOnClickListener(this);

        ImageView imgOpen = view.findViewById(R.id.img_first_run_open);
        TextView textOpen = view.findViewById(R.id.text_first_run_open);
        imgOpen.setOnClickListener(this);
        textOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_first_run_register_new || view.getId() == R.id.text_first_run_register_new) {
            if (firstRunOpenScreenFragmentCallback != null) {
                firstRunOpenScreenFragmentCallback.onFirstRunScreenButtonClicked(REGISTER_NEW_CAR_ID);
            }
        }
    }

    public void setFirstRunCallback(FirstRunOpenScreenFragmentCallback firstRunOpenScreenFragmentCallback) {
        this.firstRunOpenScreenFragmentCallback = firstRunOpenScreenFragmentCallback;
    }
}
