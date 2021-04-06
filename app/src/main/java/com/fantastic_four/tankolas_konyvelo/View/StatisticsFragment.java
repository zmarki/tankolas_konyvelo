package com.fantastic_four.tankolas_konyvelo.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.R;
import com.fantastic_four.tankolas_konyvelo.StatThreeModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.StatisticsViewModel;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class StatisticsFragment extends Fragment {

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
        statisticsViewModel.getStatThreeData().observe(getViewLifecycleOwner(), getStatThreeData);
    }

    private Observer getCountMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            List<CountSumMonth> countSumMonths = (List<CountSumMonth>) o;
            if (countSumMonths.size() > 1) {
                DataPoint dataPoints[] = new DataPoint[countSumMonths.size()];
                for (int i = 0; i < countSumMonths.size(); i++) {
                    try {
                        Date date = simpleDateFormat.parse(countSumMonths.get(i).getDate());
                        dataPoints[i] = new DataPoint(date, countSumMonths.get(i).getCount());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
                series.setColor(getActivity().getColor(R.color.darkgreen_opposite));
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                series.setValuesOnTopSize(20);
                series.setSpacing(50);
                graphView1.addSeries(series);
                graphView1.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                graphView1.getGridLabelRenderer().setNumHorizontalLabels(4);
                graphView1.getViewport().setMinY(0.0d);
                graphView1.getViewport().setScrollable(true);
                graphView1.getViewport().setScalable(true);
                graphView1.getViewport().setScrollableY(true);
                graphView1.getViewport().setScalableY(true);
                graphView1.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                graphView1.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
                graphView1.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            }
        }
    };

    private Observer getSumLiterMonthChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<CountSumMonth> literSumMonths = (List<CountSumMonth>) o;
            if (literSumMonths.size() > 1) {
                DataPoint dataPoints[] = new DataPoint[literSumMonths.size()];
                for (int i = 0; i < literSumMonths.size(); i++) {
                    dataPoints[i] = new DataPoint(i, literSumMonths.get(i).getCount());
                }
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
                series.setColor(getActivity().getColor(R.color.darkgreen_opposite));
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                series.setValuesOnTopSize(20);
                series.setSpacing(50);
                graphView2.addSeries(series);
                graphView2.getViewport().setMinY(0);
                graphView2.getViewport().setScrollable(true);
                graphView2.getViewport().setScrollableY(true);
                graphView2.getViewport().setScalable(true);
                graphView2.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                graphView2.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
                graphView2.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            }
        }
    };

    private Observer getStatThreeData = new Observer() {
        @Override
        public void onChanged(Object o) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<StatThreeModel> statThreeList = (List<StatThreeModel>) o;
            if (statThreeList.size() > 1) {
                DataPoint dataPoints[] = new DataPoint[statThreeList.size()];
                String labels[] = new String[statThreeList.size()];
                for (int i = 0; i < statThreeList.size(); i++) {
                    try {
                        dataPoints[i] = new DataPoint(simpleDateFormat.parse(statThreeList.get(i).getDate()), statThreeList.get(i).getMileageDiff());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    labels[i] = statThreeList.get(i).getDate();
                }
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
                series.setColor(getActivity().getColor(R.color.darkgreen_opposite));
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                series.setValuesOnTopSize(20);
                series.setSpacing(50);
                graphView3.addSeries(series);
                /*StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView3);
                staticLabelsFormatter.setHorizontalLabels(labels);
                graphView3.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);*/
                graphView3.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return simpleDateFormat.format(value);
                        } else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });
                graphView3.getViewport().setMinY(0);
                graphView3.getViewport().setScrollable(true);
                graphView3.getViewport().setScrollableY(true);
                graphView3.getViewport().setScalable(true);
                graphView3.getGridLabelRenderer().setGridColor(ContextCompat.getColor(getActivity(), R.color.darkgreen_opposite));
                graphView3.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
                graphView3.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(getActivity(), R.color.white));
            }
        }
    };
}
