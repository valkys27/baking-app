package com.udacity.backing_app.recipeList.presenter;

import com.udacity.backing_app.core.rx.RxSchedulers;
import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.core.model.RecipeModel;
import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.recipeList.view.RecipeListView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import javax.inject.Inject;

public class RecipeListPresenterImpl extends BasePresenter<RecipeListView> implements RecipeListPresenter {

    private final RecipeModel model;
    private final RxSchedulers rxSchedulers;
    private final RxStates rxStates;

    @Inject
    public RecipeListPresenterImpl(RecipeListView view, RecipeModel repository, RxSchedulers rxSchedulers, CompositeSubscription subscription, RxStates rxStates) {
        super(view, subscription);
        this.model = repository;
        this.rxSchedulers = rxSchedulers;
        this.rxStates = rxStates;
    }

    @Override
    public void onCreate() {
        addSubscription(getRecipeList());
        addSubscription(respondToClick());
    }

    private Subscription getRecipeList() {
        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable)
                Timber.d("no connection");
        })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.getList())
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(recipeList -> getView().swapAdapter(recipeList), Timber::e);
    }

    private Subscription respondToClick() {
        return rxStates.clickRecipe().subscribe(recipe -> getView().goToDetailActivity(recipe));
    }
}
