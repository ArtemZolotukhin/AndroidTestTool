package com.example.rz.apptesttool.mvp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rz.apptesttool.R;
import com.example.rz.apptesttool.RecyclerAdapter;
import com.example.rz.apptesttool.mvp.model.StatisticRepositoryImpl;
import com.example.rz.apptesttool.mvp.model.TouchInfo;
import com.example.rz.apptesttool.mvp.presenter.ActivityReviewPresenter;

import java.util.ArrayList;

public class ActivityReviewActivity extends AppCompatActivity implements ActivityReviewView {

    public static final String INTENT_PARAM_ACTIVITY_CLASS_NAME = "TestTool:activity_class_name";

    private ActivityReviewPresenter presenter;
    private RecyclerView recyclerView;
    private Context context = this;
    private Button mSendButton;
    private Button mCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_review);

        StatisticRepositoryImpl statisticRepository = new StatisticRepositoryImpl(context);
        mSendButton = (Button) findViewById(R.id.btn_confirm);
        mCancelButton = (Button) findViewById(R.id.btn_cancel);


        presenter = new ActivityReviewPresenter(this);
        recyclerView = findViewById(R.id.rv_criterions);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        String [] array = {"Первый критерий", "Второй критерий", "Третий критерий", "Четвертый критерий", "Пятый шоб листалось"};
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(array, context);
        recyclerView.setAdapter(recyclerAdapter);

        ( (TextView) findViewById(R.id.tv_activity_name)).setText(getIntent().getStringExtra(INTENT_PARAM_ACTIVITY_CLASS_NAME));
        ((TextView) findViewById(R.id.tv_activity_name)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));

        findViewById(R.id.btn_cancel).setOnClickListener(view -> {
            presenter.onCloseClick();
        });

    }

    @Override
    public void close() {
        finish();
    }
}
