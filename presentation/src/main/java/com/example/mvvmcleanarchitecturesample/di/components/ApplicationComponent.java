package com.example.mvvmcleanarchitecturesample.di.components;

import android.content.Context;

import com.example.mvvmcleanarchitecturesample.di.modules.ApplicationModule;
import com.example.mvvmcleanarchitecturesample.ui.base.BaseActivity;
import com.mvvmcleanarchitecturesample.domain.executor.PostExecutionThread;
import com.mvvmcleanarchitecturesample.domain.executor.ThreadExecutor;
import com.mvvmcleanarchitecturesample.domain.repository.IFeedRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    IFeedRepository userRepository();
}