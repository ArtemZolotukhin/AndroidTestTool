package com.example.rz.apptesttool.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.rz.apptesttool.R;
import com.example.rz.apptesttool.RecyclerAdapter;
import com.example.rz.apptesttool.presenter.ActivityReviewPresenter;

public class ActivityReviewActivity extends AppCompatActivity implements ActivityReviewView {

    public static final String INTENT_PARAM_ACTIVITY_CLASS_NAME = "TestTool:activity_class_name";

    private ActivityReviewPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_review);

        presenter = new ActivityReviewPresenter(this);
        recyclerView = findViewById(R.id.rv_criterions);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        String [] array = {"Первый критерий", "Второй критерий", "Третий критерий", "Четвертый критерий", "Пятый шоб листалось"};
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(array);
        recyclerView.setAdapter(recyclerAdapter);

        ( (TextView) findViewById(R.id.tv_activity_name)).setText(getIntent().getStringExtra(INTENT_PARAM_ACTIVITY_CLASS_NAME));

        findViewById(R.id.btn_cancel).setOnClickListener(view -> {
            presenter.onCloseClick();
        });

    }

    @Override
    public void close() {
        finish();
    }
}
