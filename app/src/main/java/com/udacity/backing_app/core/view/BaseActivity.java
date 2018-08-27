package com.udacity.backing_app.core.view;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.udacity.backing_app.core.presenter.Presenter;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

public abstract class BaseActivity<P extends Presenter> extends DaggerAppCompatActivity implements MvpView {

    @Inject
    P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        presenter.onCreate();
    }

    public P getPresenter() {
        return presenter;
    }

    protected void init() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
