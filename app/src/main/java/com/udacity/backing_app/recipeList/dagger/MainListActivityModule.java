package com.udacity.backing_app.recipeList.dagger;

import com.udacity.backing_app.core.rx.*;
import com.udacity.backing_app.recipeList.adapter.RecipeListAdapter;
import com.udacity.backing_app.core.model.*;
import com.udacity.backing_app.recipeList.presenter.*;
import com.udacity.backing_app.recipeList.view.*;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class MainListActivityModule {

    @Binds
    abstract RecipeListView bindView(MainListActivity mainListActivity);

    @Provides
    static RecipeListPresenter providePresenter(RecipeListView view, RecipeModel model, RxSchedulers rxSchedulers, CompositeSubscription subscription, RxStates rxStates) {
        return new RecipeListPresenterImpl(view, model, rxSchedulers, subscription, rxStates);
    }

    @Provides
    static RecipeListAdapter provideAdapter(MainListActivity mainListActivity, RxStates rxStates) {
        return new RecipeListAdapter(mainListActivity, rxStates);
    }
}
