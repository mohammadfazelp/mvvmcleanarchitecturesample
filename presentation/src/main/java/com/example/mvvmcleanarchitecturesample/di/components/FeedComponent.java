package com.example.mvvmcleanarchitecturesample.di.components;

import com.example.mvvmcleanarchitecturesample.di.PerActivity;
import com.example.mvvmcleanarchitecturesample.di.modules.ActivityModule;
import com.example.mvvmcleanarchitecturesample.di.modules.FeedModule;
import com.example.mvvmcleanarchitecturesample.ui.activity.FeedActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FeedModule.class})
public interface FeedComponent {

//    void inject(FeedActivity feedActivity);
}
