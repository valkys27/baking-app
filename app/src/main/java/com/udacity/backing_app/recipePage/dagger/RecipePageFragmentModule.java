package com.udacity.backing_app.recipePage.dagger;

import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.recipePage.adapter.StepListAdapter;
import com.udacity.backing_app.recipePage.presenter.*;
import com.udacity.backing_app.recipePage.view.*;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class RecipePageFragmentModule {

    @Binds
    abstract RecipePageView bindView(RecipePageFragment ingredientsFragment);

    @Provides
    static RecipePagePresenter providePresenter(RecipePageView view, CompositeSubscription subscription, RxStates rxStates) {
        return new RecipePagePresenterImpl(view, subscription, rxStates);
    }

    @Provides
    static StepListAdapter provideAdapter(RecipePageFragment fragment, RxStates rxStates) {
        return new StepListAdapter(fragment.getContext(), rxStates);
    }
}
