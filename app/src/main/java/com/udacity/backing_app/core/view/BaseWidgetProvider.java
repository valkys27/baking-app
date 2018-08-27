package com.udacity.backing_app.core.view;

import android.appwidget.*;
import android.content.*;
import android.support.v4.app.Fragment;
import com.udacity.backing_app.core.presenter.Presenter;
import dagger.android.*;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

public abstract class BaseWidgetProvider<P extends Presenter> extends AppWidgetProvider implements HasFragmentInjector, HasSupportFragmentInjector, MvpView {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Inject
    P presenter;

    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this, context);
        init();
        presenter.onCreate();
        super.onReceive(context, intent);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        presenter.onDestroy();
        super.onDeleted(context, appWidgetIds);
    }

    protected void init() {

    }

    public P getPresenter() {
        return presenter;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
