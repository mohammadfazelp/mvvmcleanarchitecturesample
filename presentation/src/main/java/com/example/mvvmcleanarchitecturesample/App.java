package com.example.mvvmcleanarchitecturesample;

import com.example.mvvmcleanarchitecturesample.di.components.ApplicationComponent;
import com.example.mvvmcleanarchitecturesample.di.components.DaggerApplicationComponent;
import com.example.mvvmcleanarchitecturesample.di.modules.ApplicationModule;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
