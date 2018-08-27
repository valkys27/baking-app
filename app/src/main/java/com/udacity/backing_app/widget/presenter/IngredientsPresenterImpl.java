package com.udacity.backing_app.widget.presenter;

import com.udacity.backing_app.core.model.RecipeModel;
import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.core.rx.*;
import com.udacity.backing_app.core.utils.RecipeUtils;
import com.udacity.backing_app.widget.helper.IngredientsWidgetHelper;
import com.udacity.backing_app.widget.view.IngredientsView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;

public class IngredientsPresenterImpl extends BasePresenter<IngredientsView> implements IngredientsPresenter {

    private IngredientsWidgetHelper helper;
    private RecipeModel model;
    private RxStates rxStates;
    private RxSchedulers rxSchedulers;

    @Inject
    public IngredientsPresenterImpl(IngredientsView view, IngredientsWidgetHelper helper, RecipeModel model, CompositeSubscription subscription, RxSchedulers rxSchedulers, RxStates rxStates) {
        super(view, subscription);
        this.helper = helper;
        this.model = model;
        this.rxStates = rxStates;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void setWidget(int appWidgetId) {
        helper.getRecipeIdByWidgetId(appWidgetId)
                .filter(recipeId -> recipeId != -1)
                .flatMap(recipeId -> model.findRecipeById(recipeId))
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(recipe -> updateWidget(appWidgetId, recipe));
    }

    private void updateWidget(int appWidgetId, Recipe recipe) {
        getView().setText(appWidgetId, recipe.getName(), RecipeUtils.prepareIngredients(recipe.getIngredients()));
    }

    public void deleteWidget(int appWidgetId) {
        helper.deleteRecipeIdForWidget(appWidgetId);
    }
}
