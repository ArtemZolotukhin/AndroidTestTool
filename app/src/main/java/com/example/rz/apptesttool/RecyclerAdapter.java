package com.example.rz.apptesttool;

import android.content.Context;
import android.graphics.Typeface;
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
    private Context context;

    public RecyclerAdapter (String [] array, Context context) {
        this.array = array;
        this.context = context;
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
        TextView oc;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            crit = (TextView)itemView.findViewById(R.id.param_name);
            crit.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
            bar = (SeekBar) itemView.findViewById(R.id.param_bar);
            checkBox = (CheckBox) itemView.findViewById(R.id.param_check);
            rate = (TextView) itemView.findViewById(R.id.rate);
            rate.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
            oc = (TextView)itemView.findViewById(R.id.oc);
            oc.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
        }
    }
}
