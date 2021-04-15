package com.fantastic_four.tankolas_konyvelo.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fantastic_four.tankolas_konyvelo.PrevDataModel;
import com.fantastic_four.tankolas_konyvelo.R;

import java.text.SimpleDateFormat;
import java.util.List;

//BaseAdapter korábbi tankolási adatok megjelenítéséhez
public class PrevDataAdapter extends BaseAdapter {

    private Context context;
    private List<PrevDataModel> prevDataModels;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.");

    public PrevDataAdapter(Context context, List<PrevDataModel> prevDataModels) {
        this.context = context;
        this.prevDataModels = prevDataModels;
    }

    @Override
    public int getCount() {
        return prevDataModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.prev_data_listitem_layout, viewGroup, false);
        }
        TextView dateTextView = view.findViewById(R.id.prev_data_listitem_date);
        TextView gasstationTextView = view.findViewById(R.id.prev_data_listitem_gasttation);
        TextView literTextView = view.findViewById(R.id.prev_data_listitem_liter);
        TextView kmTextView = view.findViewById(R.id.prev_data_listitem_KM);
        dateTextView.setText(simpleDateFormat.format(prevDataModels.get(i).getDate()));
        gasstationTextView.setText(prevDataModels.get(i).getGasstationName() + " (" + prevDataModels.get(i).getFuelName() + ")");
        literTextView.setText(prevDataModels.get(i).getLiter() + " L " + "(" +
                (prevDataModels.get(i).getLiter() * prevDataModels.get(i).getPrice()) + " Ft)");
        kmTextView.setText(prevDataModels.get(i).getMileage() + " KM");
        return view;
    }

    public void setNewDataSet(List<PrevDataModel> prevDataModels) {
        this.prevDataModels = prevDataModels;
        notifyDataSetChanged();
    }
}
