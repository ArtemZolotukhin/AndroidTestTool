package com.example.rz.apptesttool.mvp.view;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.rz.apptesttool.R;
import com.example.rz.apptesttool.ReviewAdapter;
import com.example.rz.apptesttool.mvp.model.Criterion;
import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.ReviewItem;
import com.example.rz.apptesttool.mvp.presenter.ActivityReviewPresenter;
import com.example.rz.apptesttool.tools.CollectionUtils;
import com.example.rz.apptesttool.tools.FragmentObjectHolder;

import java.util.HashSet;
import java.util.Set;

public class ActivityReviewActivity extends AppCompatActivity implements ActivityReviewView, SwipeRefreshLayout.OnRefreshListener {

    public static final String INTENT_PARAM_ACTIVITY_CLASS_NAME = "TestTool:activity_class_name";
    public static final String TAG_FRAGMENT_OBJECT_HOLDER = FragmentObjectHolder.class.getName();

    private ActivityReviewPresenter presenter;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private ScrollView scrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context = this;
    private Button mSendButton;
    private Button mCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_review);
        mSendButton = (Button) findViewById(R.id.btn_confirm);
        mCancelButton = (Button) findViewById(R.id.btn_cancel);

        mSendButton.setOnClickListener(view -> {

        });


        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        scrollView = findViewById(R.id.box_criteries);
        recyclerView = findViewById(R.id.rv_criterions);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        reviewAdapter = new ReviewAdapter(context);
        recyclerView.setAdapter(reviewAdapter);

        ( (TextView) findViewById(R.id.tv_activity_name)).setText(getIntent().getStringExtra(INTENT_PARAM_ACTIVITY_CLASS_NAME));
        ((TextView) findViewById(R.id.tv_activity_name)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));

        findViewById(R.id.btn_cancel).setOnClickListener(view -> {
            presenter.onCloseClick();
        });

        presenter = getPresenter();
    }

    private ActivityReviewPresenter getPresenter() {
        if (presenter != null) {
            return presenter;
        }
        FragmentObjectHolder<ActivityReviewPresenter> fragmentObjectHolder = getFragmentObjectHolder();
        presenter = fragmentObjectHolder.getContent();
        if (presenter == null) {
            presenter = new ActivityReviewPresenter(this);
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
        swipeRefreshLayout.setRefreshing(isLoading);
        scrollView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void updateCriterions(Set<Criterion> criterionSet) {
        Set<ReviewItem> reviewItemList = toReviewItemSet(criterionSet);
        CollectionUtils collectionUtils = new CollectionUtils();
        reviewAdapter.setItems(collectionUtils.toList(reviewItemList));
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
        //TODO getReview
        return null;
    }

    @Override
    public void showError(int errorCode) {
        //TODO show error
    }

    @Override
    public void onRefresh() {
        presenter.loadCriterions();
    }
}
