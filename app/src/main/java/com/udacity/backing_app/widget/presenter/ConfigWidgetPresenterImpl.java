package com.udacity.backing_app.widget.presenter;

import com.udacity.backing_app.core.model.RecipeModel;
import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.core.rx.RxSchedulers;
import com.udacity.backing_app.widget.helper.IngredientsWidgetHelper;
import com.udacity.backing_app.widget.view.ConfigWidgetView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import javax.inject.Inject;

public class ConfigWidgetPresenterImpl extends BasePresenter<ConfigWidgetView> implements ConfigWidgetPresenter {

    private RecipeModel model;
    private IngredientsWidgetHelper helper;
    private RxSchedulers rxSchedulers;

    @Inject
    public ConfigWidgetPresenterImpl(ConfigWidgetView view, RecipeModel model, IngredientsWidgetHelper helper, CompositeSubscription subscription, RxSchedulers rxSchedulers) {
        super(view, subscription);
        this.model = model;
        this.helper = helper;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onCreate() {
        addSubscription(getRecipeList());
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
}
