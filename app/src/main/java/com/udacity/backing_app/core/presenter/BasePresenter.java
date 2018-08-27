package com.udacity.backing_app.core.presenter;

import com.udacity.backing_app.core.view.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends MvpView> implements Presenter {

    private V view;
    private CompositeSubscription subscription;

    public BasePresenter(V view, CompositeSubscription subscription) {
        this.view = view;
        this.subscription = subscription;
    }

    @Override
    public void onDestroy() {
        subscription.clear();
    }

    public V getView() {
        return view;
    }

    protected void addSubscription(Subscription subscription) {
        this.subscription.add(subscription);
    }
}
