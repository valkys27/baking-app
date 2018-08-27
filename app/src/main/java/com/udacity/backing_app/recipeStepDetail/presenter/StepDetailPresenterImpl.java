package com.udacity.backing_app.recipeStepDetail.presenter;

import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.recipeStepDetail.view.StepDetailView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;

public class StepDetailPresenterImpl extends BasePresenter<StepDetailView> implements StepDetailPresenter {

    private final RxStates rxStates;

    @Inject
    public StepDetailPresenterImpl(StepDetailView view, CompositeSubscription subscription, RxStates rxStates) {
        super(view, subscription);
        this.rxStates = rxStates;
    }

    @Override
    public void onCreate() {
        addSubscription(getStep());
    }

    private Subscription getStep() {
        return rxStates.stepSubject().subscribe(step -> getView().init(step));
    }
}
