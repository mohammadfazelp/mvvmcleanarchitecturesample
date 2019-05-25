package com.example.mvvmcleanarchitecturesample.ui.activity;

import com.example.mvvmcleanarchitecturesample.R;
import com.example.mvvmcleanarchitecturesample.databinding.FeedActivityBinding;
import com.example.mvvmcleanarchitecturesample.di.HasComponent;
import com.example.mvvmcleanarchitecturesample.di.components.FeedComponent;
import com.example.mvvmcleanarchitecturesample.ui.adapter.FeedAdapter;
import com.example.mvvmcleanarchitecturesample.ui.base.BaseActivity;
import com.example.mvvmcleanarchitecturesample.viewmodel.FeedViewModel;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmcleanarchitecturesample.di.components.DaggerFeedComponent;

public class FeedActivity extends BaseActivity/* implements HasComponent<FeedComponent>*/ {

    private FeedComponent component;

    @Inject
     FeedAdapter adapter;

    @Inject
    FeedViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed;
    }

    private FeedActivityBinding binding;

    @Override
    protected void doLogic() {
        initInjector();
        viewModel.initialize();
        binding.listFeed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.getFeedLiveData().observe(this,
                pagedList -> adapter.addItems((ArrayList) Collections.EMPTY_LIST));
        binding.listFeed.setAdapter(adapter);
    }

    private void initInjector() {
        this.component = DaggerFeedComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

//    @Override
//    public FeedComponent getComponent() {
//        return component;
//    }
}