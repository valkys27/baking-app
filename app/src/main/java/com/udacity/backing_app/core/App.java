package com.udacity.backing_app.core;

import com.udacity.backing_app.BuildConfig;
import com.udacity.backing_app.core.dagger.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import org.jetbrains.annotations.*;
import timber.log.Timber;

public class App extends DaggerApplication {

    public static String PACKAGE_NAME;

    @Override
    public void onCreate() {
        super.onCreate();
        PACKAGE_NAME = getPackageName();
        initLogger();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {

                }
            });
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
