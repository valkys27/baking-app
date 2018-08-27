package com.udacity.backing_app.recipePage.presenter;

import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.core.utils.RecipeUtils;
import com.udacity.backing_app.recipePage.view.RecipePageView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;

public class RecipePagePresenterImpl extends BasePresenter<RecipePageView> implements RecipePagePresenter {

    private RxStates rxStates;

    @Inject
    public RecipePagePresenterImpl(RecipePageView view, CompositeSubscription subscription, RxStates rxStates) {
        super(view, subscription);
        this.rxStates = rxStates;
    }

    @Override
    public void onCreate() {
        addSubscription(getRecipe());
    }

    private Subscription getRecipe() {
        return rxStates.recipeSubject().subscribe(recipe -> {
            getView().setIngredients(RecipeUtils.prepareIngredients(recipe.getIngredients()));
            getView().swapAdapter(recipe.getSteps());
        });
    }
}