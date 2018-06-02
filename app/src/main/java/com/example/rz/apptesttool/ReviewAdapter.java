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

import com.example.rz.apptesttool.mvp.model.ReviewItem;

import java.util.List;

/**
 * Created by Марат on 22.03.2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;

    private List<ReviewItem> reviewItems;

    public ReviewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_criterion, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        if (reviewItems != null) {
            ReviewItem item = reviewItems.get(position);
            if (item != null) {
                holder.update(item);
            }
        }
    }

    @Override
    public int getItemCount() {
        return reviewItems == null ? 0 : reviewItems.size();
    }

    public void setItems(List<ReviewItem> reviewItems) {
        this.reviewItems = reviewItems;
        notifyDataSetChanged();
    }

    public List<ReviewItem> getReviewItems() {
        return this.reviewItems;
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        private ReviewItem reviewItem;
        private View boxBar;
        private TextView criterionName;
        private TextView rate;
        private SeekBar bar;
        private CheckBox checkBox;
        private TextView oc;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            boxBar = itemView.findViewById(R.id.box_bar);
            criterionName = itemView.findViewById(R.id.param_name);
            criterionName.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
            bar = itemView.findViewById(R.id.param_bar);
            rate = itemView.findViewById(R.id.tv_rate);
            rate.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
            oc = itemView.findViewById(R.id.tv_rate_title);
            oc.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
            checkBox = itemView.findViewById(R.id.param_check);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                setActive(checkBox.isChecked());
            });
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (reviewItem != null) {
                        int newValue = seekBar.getProgress() + reviewItem.getMinValue();
                        ReviewViewHolder.this.reviewItem.setValue(newValue);
                        rate.setText(String.valueOf(newValue));
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }

        /**
         * You should call this method after boxBar initialization, else you get NullPointerException
         * @param active
         */
        public void setActive(boolean active) {
            if (reviewItem != null) {
                reviewItem.setChecked(checkBox.isChecked());
            }
            boxBar.setVisibility(active ? View.VISIBLE : View.GONE);
        }

        public void update(ReviewItem reviewItem) {
            this.reviewItem = reviewItem;
            criterionName.setText(reviewItem.getName());
            rate.setText(String.valueOf(reviewItem.getValue()));
            checkBox.setChecked(reviewItem.isChecked());
            setActive(reviewItem.isChecked());
            bar.setMax(reviewItem.getMaxValue() - reviewItem.getMinValue());

        }
    }
}
