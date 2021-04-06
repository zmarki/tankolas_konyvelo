package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class PrevDataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prev_data_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<PrevDataModel> prevDataModels = new ArrayList<>();
        ListView listView = view.findViewById(R.id.prev_data_fragment_listview);
        PrevDataAdapter prevDataAdapter = new PrevDataAdapter(getActivity(), prevDataModels);
        listView.setAdapter(prevDataAdapter);

        PersonalChalkViewModel personalChalkViewModel = new ViewModelProvider(requireActivity()).get(PersonalChalkViewModel.class);
        personalChalkViewModel.getAllDataWithGSFuelName().observe(getViewLifecycleOwner(), new Observer<List<PrevDataModel>>() {
            @Override
            public void onChanged(List<PrevDataModel> prevDataModels) {
                prevDataAdapter.setNewDataSet(prevDataModels);
            }
        });

    }
}
