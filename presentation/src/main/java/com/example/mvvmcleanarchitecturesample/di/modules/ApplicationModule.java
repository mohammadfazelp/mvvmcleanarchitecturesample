package com.example.mvvmcleanarchitecturesample.di.modules;


import android.content.Context;

import com.example.mvvmcleanarchitecturesample.App;
import com.example.mvvmcleanarchitecturesample.UIThread;
import com.mvvmcleanarchitecturesample.data.cache.FeedCache;
import com.mvvmcleanarchitecturesample.data.cache.FeedCacheImpl;
import com.mvvmcleanarchitecturesample.data.executor.JobExecutor;
import com.mvvmcleanarchitecturesample.data.repository.FeedDataRepository;
import com.mvvmcleanarchitecturesample.domain.executor.PostExecutionThread;
import com.mvvmcleanarchitecturesample.domain.executor.ThreadExecutor;
import com.mvvmcleanarchitecturesample.domain.repository.IFeedRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    FeedCache provideUserCache(FeedCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    IFeedRepository provideUserRepository(FeedDataRepository userDataRepository) {
        return userDataRepository;
    }
}

