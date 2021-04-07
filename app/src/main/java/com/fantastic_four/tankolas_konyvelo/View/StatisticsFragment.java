package com.fantastic_four.tankolas_konyvelo.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.ValueDataEntry;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.R;
import com.fantastic_four.tankolas_konyvelo.StatThreeModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.StatisticsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class StatisticsFragment extends Fragment {

    private AnyChartView graphView1;
    private AnyChartView graphView2;
    private AnyChartView graphView3;

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
        statisticsViewModel.getStatThreeData().observe(getViewLifecycleOwner(), getStatThreeData);
    }

    private Observer getCountMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<CountSumMonth> countSumMonths = (List<CountSumMonth>) o;
            if (countSumMonths.size() > 0) {
                Cartesian bar = AnyChart.column();
                List<DataEntry> data = new ArrayList<>();
                for (int i = 0; i < countSumMonths.size(); i++) {
                    data.add(new ValueDataEntry(countSumMonths.get(i).getDate(), countSumMonths.get(i).getCount()));
                }
                bar.setData(data);
                bar.getXAxis().setTitle("Dátum");
                bar.getYAxis().setTitle("db");
                setBarStyle(bar);
                graphView1.setChart(bar);
            } else {
                Cartesian bar = AnyChart.column();
                bar.getBackground().fill("#02240E", 1d);
                graphView1.setChart(bar);
            }
        }
    };

    private Observer getSumLiterMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<CountSumMonth> literSumMonths = (List<CountSumMonth>) o;
            if (literSumMonths.size() > 0) {
                Cartesian bar = AnyChart.column();
                List<DataEntry> data = new ArrayList<>();
                for (int i = 0; i < literSumMonths.size(); i++) {
                    data.add(new ValueDataEntry(literSumMonths.get(i).getDate(), literSumMonths.get(i).getCount()));
                }
                bar.setData(data);
                bar.getXAxis().setTitle("Dátum");
                bar.getYAxis().setTitle("liter");
                setBarStyle(bar);
                graphView2.setChart(bar);
            } else {
                Cartesian bar = AnyChart.column();
                bar.getBackground().fill("#02240E", 1d);
                graphView2.setChart(bar);
            }
        }
    };

    private Observer getStatThreeData = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<StatThreeModel> statThreeList = (List<StatThreeModel>) o;
            if (statThreeList.size() > 0) {
                Cartesian bar = AnyChart.column();
                List<DataEntry> data = new ArrayList<>();
                for (int i = 0; i < statThreeList.size(); i++) {
                    data.add(new ValueDataEntry(statThreeList.get(i).getDate(), statThreeList.get(i).getMileageDiff()));
                }
                bar.setData(data);
                bar.getXAxis().setTitle("Dátum");
                bar.getYAxis().setTitle("km");
                setBarStyle(bar);
                graphView3.setChart(bar);
            } else {
                Cartesian bar = AnyChart.column();
                bar.getBackground().fill("#02240E", 1d);
                graphView3.setChart(bar);
            }
        }
    };

    private void setBarStyle(Cartesian bar) {
        bar.getXAxis().getTitle().setFontColor("#FFFFFF");
        bar.getYAxis().getTitle().setFontColor("#FFFFFF");
        bar.getBackground().fill("#02240E", 1d);
        bar.getLabels().setEnabled(true);
        bar.getLabels().setFontColor("#000000");
        bar.getXAxis().getLabels().setFontColor("#FFFFFF");
        bar.getYAxis().getLabels().setFontColor("#FFFFFF");
        bar.getYScale().getTicks().setInterval(1d);
    }
}
