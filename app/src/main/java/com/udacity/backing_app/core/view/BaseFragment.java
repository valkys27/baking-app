package com.udacity.backing_app.core.view;

import android.os.Bundle;
import android.support.annotation.*;
import android.view.LayoutInflater;
import android.view.*;
import butterknife.ButterKnife;
import com.udacity.backing_app.core.presenter.Presenter;
import dagger.android.support.DaggerFragment;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public abstract class BaseFragment<P extends Presenter> extends DaggerFragment implements MvpView {

    @Inject
    P presenter;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), parent, false);
        ButterKnife.bind(this, view);
        init();
        presenter.onCreate();
        return view;
    }

    protected void init() {

    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
    }

    protected P getPresenter() {
        return presenter;
    }
}
