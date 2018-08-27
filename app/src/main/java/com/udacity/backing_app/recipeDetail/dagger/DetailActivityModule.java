package com.udacity.backing_app.recipeDetail.dagger;

import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.recipeDetail.adapter.FragmentPagerAdapter;
import com.udacity.backing_app.recipeDetail.presenter.*;
import com.udacity.backing_app.recipeDetail.view.*;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class DetailActivityModule {

    @Binds
    abstract RecipeDetailView bindView(DetailActivity detailActivity);

    @Provides
    static RecipeDetailPresenter providePresenter(RecipeDetailView view, CompositeSubscription subscription, RxStates rxStates) {
        return new RecipeDetailPresenterImpl(view, subscription, rxStates);
    }

    @Provides
    static FragmentPagerAdapter provideAdapter(RxStates rxStates) {
        return new FragmentPagerAdapter(rxStates);
    }
}
