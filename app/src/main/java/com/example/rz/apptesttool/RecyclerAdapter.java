package com.example.rz.apptesttool;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Марат on 22.03.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.AdapterViewHolder> {

    private String [] array;

    public RecyclerAdapter (String [] array) {
        this.array = array;
    }



    @Override
    public RecyclerAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_criterion, parent, false);
        return new RecyclerAdapter.AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.AdapterViewHolder holder, int position) {
          holder.crit.setText(array[position]);
          holder.rate.setText("0");
          holder.bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                  holder.rate.setText(String.valueOf(seekBar.getProgress()));
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

              }
          });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView crit;
        TextView rate;
        SeekBar bar;
        CheckBox checkBox;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            crit = (TextView)itemView.findViewById(R.id.param_name);
            bar = (SeekBar) itemView.findViewById(R.id.param_bar);
            checkBox = (CheckBox) itemView.findViewById(R.id.param_check);
            rate = (TextView) itemView.findViewById(R.id.rate);
        }
    }
}
