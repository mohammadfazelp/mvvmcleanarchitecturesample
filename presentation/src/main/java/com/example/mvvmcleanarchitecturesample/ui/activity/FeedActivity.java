package com.example.mvvmcleanarchitecturesample.ui.activity;

import com.example.mvvmcleanarchitecturesample.R;
import com.example.mvvmcleanarchitecturesample.di.HasComponent;
import com.example.mvvmcleanarchitecturesample.di.components.DaggerFeedComponent;
import com.example.mvvmcleanarchitecturesample.di.components.FeedComponent;
import com.example.mvvmcleanarchitecturesample.ui.base.BaseActivity;
import com.example.mvvmcleanarchitecturesample.viewmodel.FeedViewModel;

import javax.inject.Inject;

public class FeedActivity extends BaseActivity implements HasComponent<FeedComponent> {

    private FeedComponent component;

    @Inject
    FeedViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed;
    }

    @Override
    protected void doLogic() {
        initInjector();
        viewModel.initialize();
    }

    private void initInjector() {
        this.component = DaggerFeedComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public FeedComponent getComponent() {
        return component;
    }
}