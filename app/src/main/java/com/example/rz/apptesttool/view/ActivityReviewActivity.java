package com.example.rz.apptesttool.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rz.apptesttool.R;
import com.example.rz.apptesttool.presenter.ActivityReviewPresenter;

public class ActivityReviewActivity extends AppCompatActivity implements ActivityReviewView {

    public static final String INTENT_PARAM_ACTIVITY_CLASS_NAME = "TestTool:activity_class_name";

    private ActivityReviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_review);

        presenter = new ActivityReviewPresenter(this);

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
