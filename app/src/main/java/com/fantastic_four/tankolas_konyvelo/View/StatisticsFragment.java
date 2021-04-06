package com.fantastic_four.tankolas_konyvelo.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.R;
import com.fantastic_four.tankolas_konyvelo.ViewModel.StatisticsViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class StatisticsFragment extends Fragment {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");

    private GraphView graphView1;
    private GraphView graphView2;
    private GraphView graphView3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.statistics_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StatisticsViewModel statisticsViewModel = new ViewModelProvider(requireActivity()).get(StatisticsViewModel.class);

        graphView1 = view.findViewById(R.id.statistics_first_stat);
        graphView2 = view.findViewById(R.id.statistics_second_stat);
        graphView3 = view.findViewById(R.id.statistics_third_stat);

        statisticsViewModel.getCountMonthChalk().observe(getViewLifecycleOwner(), getCountMonthChalk);
        statisticsViewModel.getSumLiterMonthChalk().observe(getViewLifecycleOwner(), getSumLiterMonthChalk);
        statisticsViewModel.getAllData().observe(getViewLifecycleOwner(), getAllPersonalChalks);
    }

    private Observer getCountMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<CountSumMonth> countSumMonths = (List<CountSumMonth>) o;
            DataPoint dataPoints[] = new DataPoint[countSumMonths.size()];
            String labels[] = new String[countSumMonths.size()];
            for (int i = 0; i < countSumMonths.size(); i++) {
                dataPoints[i] = new DataPoint(i, countSumMonths.get(i).getCount());
                labels[i] = countSumMonths.get(i).getDate();
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.red));
            series.setValuesOnTopSize(20);
            series.setSpacing(50);
            graphView1.addSeries(series);
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView1);
            staticLabelsFormatter.setHorizontalLabels(labels);
            graphView1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graphView1.getViewport().setMinY(0);
            graphView1.getViewport().setScrollable(true);
            graphView1.getViewport().setScrollableY(true);
            graphView1.getViewport().setScalable(true);
            graphView1.getViewport().setScalableY(true);
            graphView1.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView1.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView1.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
        }
    };

    private Observer getSumLiterMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<CountSumMonth> literSumMonths = (List<CountSumMonth>) o;
            DataPoint dataPoints[] = new DataPoint[literSumMonths.size()];
            String labels[] = new String[literSumMonths.size()];
            for (int i = 0; i < literSumMonths.size(); i++) {
                dataPoints[i] = new DataPoint(i, literSumMonths.get(i).getCount());
                labels[i] = literSumMonths.get(i).getDate();
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.red));
            series.setValuesOnTopSize(20);
            series.setSpacing(50);
            graphView2.addSeries(series);
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView2);
            staticLabelsFormatter.setHorizontalLabels(labels);
            graphView2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graphView2.getViewport().setMinY(0);
            graphView2.getViewport().setScrollable(true);
            graphView2.getViewport().setScrollableY(true);
            graphView2.getViewport().setScalable(true);
            graphView2.getViewport().setScalableY(true);
            graphView2.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView2.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView2.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
        }
    };

    private Observer getAllPersonalChalks = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<PersonalChalk> personalChalkList = (List<PersonalChalk>) o;
            DataPoint dataPoints[] = new DataPoint[personalChalkList.size() - 1];
            String labels[] = new String[personalChalkList.size() - 1];
            for (int i = 0; i < personalChalkList.size() - 1; i++) {
                dataPoints[i] = new DataPoint(i, personalChalkList.get(i + 1).mileage - personalChalkList.get(i).mileage);
                labels[i] = simpleDateFormat.format(personalChalkList.get(i).date);
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.red));
            series.setValuesOnTopSize(20);
            series.setSpacing(50);
            graphView3.addSeries(series);
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView3);
            staticLabelsFormatter.setHorizontalLabels(labels);
            graphView3.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graphView3.getViewport().setMinY(0);
            graphView3.getViewport().setScrollable(true);
            graphView3.getViewport().setScrollableY(true);
            graphView3.getViewport().setScalable(true);
            graphView3.getViewport().setScalableY(true);
            graphView3.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView3.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            graphView3.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
        }
    };
}
