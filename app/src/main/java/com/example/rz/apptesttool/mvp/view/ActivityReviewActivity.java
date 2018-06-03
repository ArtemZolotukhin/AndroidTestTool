package com.example.rz.apptesttool.mvp.view;

import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rz.apptesttool.R;
import com.example.rz.apptesttool.ReviewAdapter;
import com.example.rz.apptesttool.mvp.model.Criterion;
import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.ReviewItem;
import com.example.rz.apptesttool.mvp.presenter.ActivityReviewPresenter;
import com.example.rz.apptesttool.tools.CollectionUtils;
import com.example.rz.apptesttool.tools.FragmentObjectHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityReviewActivity extends AppCompatActivity implements ActivityReviewView, SwipeRefreshLayout.OnRefreshListener {

    public static final String INTENT_PARAM_ACTIVITY_CLASS_NAME = "TestTool:activity_class_name";
    public static final String TAG_FRAGMENT_OBJECT_HOLDER = FragmentObjectHolder.class.getName();

    public static final String PARAM_IS_ERROR = ActivityReviewActivity.class.getClass().getName() + ":is_error";
    public static final String PARAM_REVIEW = ActivityReviewActivity.class.getClass().getName() + ":review";
    public static final String PARAM_IS_LOADING = ActivityReviewActivity.class.getClass().getName() + ":is_loading";

    private ActivityReviewPresenter presenter;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private ScrollView scrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button mSendButton;
    private Button mCancelButton;
    private View boxSend;
    private View vError;
    private boolean isError;
    private EditText etReview;
    private boolean isLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_review);
        isError = false;
        isLoading = false;
        mSendButton = findViewById(R.id.btn_confirm);
        mCancelButton = findViewById(R.id.btn_cancel);
        etReview = findViewById(R.id.et_review);
        vError = findViewById(R.id.tv_error);
        boxSend = findViewById(R.id.box_send);

        mSendButton.setOnClickListener(view -> {
            if (presenter  != null) {
                presenter.onSendClick();
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        scrollView = findViewById(R.id.box_criteries);
        recyclerView = findViewById(R.id.rv_criterions);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        reviewAdapter = new ReviewAdapter(this);
        recyclerView.setAdapter(reviewAdapter);

        ( (TextView) findViewById(R.id.tv_activity_name)).setText(getIntent().getStringExtra(INTENT_PARAM_ACTIVITY_CLASS_NAME));
        ((TextView) findViewById(R.id.tv_activity_name)).setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf"));

        findViewById(R.id.btn_cancel).setOnClickListener(view -> {
            presenter.onCloseClick();
        });

        presenter = getPresenter();

        loadState(savedInstanceState);
    }

    private void loadState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.isError = savedInstanceState.getBoolean(PARAM_IS_ERROR, false);
            this.isLoading = savedInstanceState.getBoolean(PARAM_IS_LOADING);
        }
        setLoading(isLoading);
        setIsError(isError);
        Review review = (Review) presenter.getFromUglyStorage(PARAM_REVIEW);
        if (review != null) {
            CollectionUtils collectionUtils = new CollectionUtils();
            reviewAdapter.setItems(collectionUtils.toList(review.getReviewItemSet()));
        }
    }
    private void saveState(Bundle outState) {
        outState.putBoolean(PARAM_IS_ERROR, isError);
        outState.putBoolean(PARAM_IS_LOADING, isLoading);
        getPresenter().putToUglyStorage(PARAM_REVIEW, getReview());
    }


    private ActivityReviewPresenter getPresenter() {
        if (presenter != null) {
            presenter.setView(this);
            return presenter;
        }
        FragmentObjectHolder<ActivityReviewPresenter> fragmentObjectHolder = getFragmentObjectHolder();
        presenter = fragmentObjectHolder.getContent();
        if (presenter == null) {
            presenter = new ActivityReviewPresenter(this);
        } else {
            presenter.setView(this);
        }
        fragmentObjectHolder.setContent(presenter);
        return presenter;
    }

    private FragmentObjectHolder<ActivityReviewPresenter> getFragmentObjectHolder() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentObjectHolder<ActivityReviewPresenter> fragmentObjectHolder =
                (FragmentObjectHolder<ActivityReviewPresenter>) fragmentManager
                        .findFragmentByTag(TAG_FRAGMENT_OBJECT_HOLDER);
        if (fragmentObjectHolder == null) {
            fragmentObjectHolder = new FragmentObjectHolder<>();
            fragmentManager.beginTransaction().add(fragmentObjectHolder, TAG_FRAGMENT_OBJECT_HOLDER).commit();
        }
        return fragmentObjectHolder;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().setView(null);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            setIsError(false);
        }
        this.isLoading = isLoading;
        swipeRefreshLayout.setEnabled(isLoading);
        swipeRefreshLayout.setRefreshing(isLoading);
        scrollView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void updateCriterions(Set<Criterion> criterionSet) {
        Set<ReviewItem> reviewItemList = toReviewItemSet(criterionSet);
        CollectionUtils collectionUtils = new CollectionUtils();
        updateCriterions(collectionUtils.toList(reviewItemList));
    }

    private void updateCriterions(List<ReviewItem> items) {
        reviewAdapter.setItems(items);
    }

    private Set<ReviewItem> toReviewItemSet(Set<Criterion> criterionSet) {
        Set<ReviewItem> set = new HashSet<>();
        for (Criterion i : criterionSet) {
            if (i != null) {
                set.add(toReviewItem(i));
            }
        }
        return set;
    }

    private ReviewItem toReviewItem(Criterion criterion) {
        return new ReviewItem(criterion.getId(), criterion.getMinValue(), criterion.getName(),
                criterion.getMinValue(), criterion.getMaxValue());
    }

    @Override
    public Review getReview() {
        if (reviewAdapter.getItemCount() == 0) {
           return null;
        }
        Review review = new Review();
        //TODO normal activity class name
        review.setDisplayName(getIntent().getStringExtra(INTENT_PARAM_ACTIVITY_CLASS_NAME));
        review.setMessage(etReview.getText().toString());
        CollectionUtils collectionUtils = new CollectionUtils();
        review.setReviewItemSet(collectionUtils.toSet(reviewAdapter.getReviewItems()));
        return review;
    }

    @Override
    public void showError(int errorCode) {
        switch (errorCode) {
            case ERROR_CODE_CRITERIONS_LOAD:
                setIsError(true);
                break;
            case ERROR_CODE_SEND:
                Toast.makeText(this, R.string.error_send, Toast.LENGTH_LONG).show();
                break;

        }
    }

    @Override
    public void onRefresh() {
        presenter.loadCriterions();
    }

    public void setIsError(boolean isError) {
        if (isError) {
            swipeRefreshLayout.setEnabled(true);
        }
        this.isError = isError;
        boxSend.setVisibility(isError ? View.GONE : View.VISIBLE);
        vError.setVisibility(isError ? View.VISIBLE : View.GONE);
    }
}
