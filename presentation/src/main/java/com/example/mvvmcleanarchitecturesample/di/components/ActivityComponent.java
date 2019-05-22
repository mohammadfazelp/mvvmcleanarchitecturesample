package com.example.mvvmcleanarchitecturesample.di.components;


import android.app.Activity;

import com.example.mvvmcleanarchitecturesample.di.modules.ActivityModule;
import com.example.mvvmcleanarchitecturesample.di.PerActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
