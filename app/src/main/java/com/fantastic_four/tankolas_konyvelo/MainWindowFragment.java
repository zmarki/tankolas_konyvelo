package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MainWindowFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mainViewModel;

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

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        ImageView addNewFillingImg = view.findViewById(R.id.img_add_new_filling);
        TextView addNewFillingText = view.findViewById(R.id.text_add_new_filling);
        addNewFillingText.setOnClickListener(this);
        addNewFillingImg.setOnClickListener(this);

        GraphView graphView = view.findViewById(R.id.graph_main_screen);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(3, 5),
                new DataPoint(8, 9),
        });
        graphView.addSeries(series);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_add_new_filling || view.getId() == R.id.text_add_new_filling) {
            mainViewModel.setClickedButtonId(MainActivity.NEWFILLINGFRAGMENT_ID);
        }
    }
}
