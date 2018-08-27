package com.udacity.backing_app.recipeStepDetail.dagger;

import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.recipeStepDetail.presenter.StepDetailPresenter;
import com.udacity.backing_app.recipeStepDetail.presenter.StepDetailPresenterImpl;
import com.udacity.backing_app.recipeStepDetail.view.StepDetailFragment;
import com.udacity.backing_app.recipeStepDetail.view.StepDetailView;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class StepDetailFragmentModule {

    @Binds
    abstract StepDetailView bindView(StepDetailFragment stepVideoFragment);

    @Provides
    static StepDetailPresenter providePresenter(StepDetailView view, CompositeSubscription subscription, RxStates rxStates) {
        return new StepDetailPresenterImpl(view, subscription, rxStates);
    }
}
