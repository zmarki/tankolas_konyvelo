package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.StatisticsViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainWindowFragment extends Fragment implements View.OnClickListener {

   /* nyitóképernyőre korábbi tankolások adatai(pl a legutóbbi 5 tankolás) -> adatpárok: tankolás ideje - tankolás mennyisége - tankolás ára
    tankolások száma havonta -> adatpárok: hónap - tankolások (bejegyzések - count) száma
    hány liter üzemanyag lett tankolva havonta -> adatpárok: hónap - tankolt benzin mennyisége (sum)
    egy tankolással hány km lett megtéve -> adatpárok: tankolás dátuma - ez utáni és az adott tankoláskori km óraállás különbsége (kivéve legutolsó tankolás)
*/

    private MainViewModel mainViewModel;
    private CarViewModel carViewModel;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");
    private DecimalFormat df = new DecimalFormat("###.##");

    private GraphView graphView;

    private TextView actualCarDetailsTextview;
    private TextView lastFillingTextView;
    private TextView avgFillingAmountTextView;
    private TextView avgConsumptionTextView;
    private TextView avgFillingKMTextView;
    private TextView avgFillingDurationTextView;

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

        actualCarDetailsTextview = view.findViewById(R.id.text_actual_car_details);
        lastFillingTextView = view.findViewById(R.id.text_last_filling_details);
        avgFillingAmountTextView = view.findViewById(R.id.text_avg_filling_details);
        avgConsumptionTextView = view.findViewById(R.id.text_avg_consumption_details);
        avgFillingKMTextView = view.findViewById(R.id.text_avg_km_per_filling_details);
        avgFillingDurationTextView = view.findViewById(R.id.text_avg_filling_time_details);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        carViewModel = new ViewModelProvider((requireActivity())).get(CarViewModel.class);
        StatisticsViewModel statisticsViewModel = new ViewModelProvider(requireActivity()).get(StatisticsViewModel.class);
        statisticsViewModel.getLastChalk().observe(getViewLifecycleOwner(), getLastChalk);
        statisticsViewModel.getAvgFilling().observe(getViewLifecycleOwner(), getAvgFilling);
        statisticsViewModel.getAvgConsumption().observe(getViewLifecycleOwner(), getAvgConsumption);
        statisticsViewModel.getAvgFillingKM().observe(getViewLifecycleOwner(), getAvgFillingKM);
        statisticsViewModel.getAvgFillingDuration().observe(getViewLifecycleOwner(), getAvgFillingDuration);
        statisticsViewModel.getLastFiveChalk().observe(getViewLifecycleOwner(), getLastFiveChalk);

        carViewModel.getAllCars().observe(getViewLifecycleOwner(), getAllCars);

        ImageView addNewFillingImg = view.findViewById(R.id.img_add_new_filling);
        TextView addNewFillingText = view.findViewById(R.id.text_add_new_filling);
        addNewFillingText.setOnClickListener(this);
        addNewFillingImg.setOnClickListener(this);

        graphView = view.findViewById(R.id.graph_main_screen);
    }

    private Observer getAllCars = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<Car> cars = (List<Car>) o;
            Car car = cars.get(0);
            actualCarDetailsTextview.setText(car.brand + " " + car.type + " "
                    + car.licensePlateNumber);
        }
    };

    private Observer getLastChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            LastFive lastFive = (LastFive) o;
            lastFillingTextView.setText(simpleDateFormat.format(lastFive.getDate()) +
                    " - " + lastFive.getLiter() + "L; " +
                    lastFive.getGSName() + " (" + lastFive.getFuelName() + ")");
        }
    };

    private Observer getAvgFilling = new Observer() {
        @Override
        public void onChanged(Object o) {
            Float amount = (Float) o;
            avgFillingAmountTextView.setText(df.format(amount) + "L");
        }
    };

    private Observer getAvgConsumption = new Observer() {
        @Override
        public void onChanged(Object o) {
            Double amount = (Double) o;
            avgConsumptionTextView.setText(df.format(amount) + "L/km");
        }
    };

    private Observer getAvgFillingKM = new Observer() {
        @Override
        public void onChanged(Object o) {
            Double amount = (Double) o;
            avgFillingKMTextView.setText(df.format(amount) + "km");
        }
    };

    private Observer getAvgFillingDuration = new Observer() {
        @Override
        public void onChanged(Object o) {
            Double amount = (Double) o;
            avgFillingDurationTextView.setText(df.format(amount) + "nap");
        }
    };

    private Observer getLastFiveChalk = new Observer() {
        @Override
        public void onChanged(Object o) {
            List<LastFive> lastFives = (List<LastFive>) o;
            DataPoint dataPoints[] = new DataPoint[lastFives.size()];
            for (int i = 0; i < lastFives.size(); i++) {
                dataPoints[i] = new DataPoint(lastFives.get(i).getDate().getTime(), lastFives.get(i).getLiter());
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setColor(getActivity().getColor(R.color.lightgreen));
            graphView.addSeries(series);
            graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
            graphView.getGridLabelRenderer().setGridColor(R.color.white);
            graphView.getGridLabelRenderer().setHorizontalLabelsColor(R.color.white);
            graphView.getGridLabelRenderer().setVerticalLabelsColor(R.color.white);
            graphView.getViewport().setMinY(0);
            graphView.getViewport().setMinX(lastFives.get(0).getDate().getTime());
            graphView.getViewport().setMaxX(lastFives.get(lastFives.size() - 1).getDate().getTime() + 3000);
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_add_new_filling || view.getId() == R.id.text_add_new_filling) {
            mainViewModel.setClickedButtonId(MainActivity.NEWFILLINGFRAGMENT_ID);
        }
    }
}
