package com.udacity.backing_app.recipeDetail.presenter;

import com.udacity.backing_app.core.presenter.BasePresenter;
import com.udacity.backing_app.core.rx.RxStates;
import com.udacity.backing_app.recipeDetail.view.RecipeDetailView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;

public class RecipeDetailPresenterImpl extends BasePresenter<RecipeDetailView> implements RecipeDetailPresenter {

    private RxStates rxStates;

    @Inject
    public RecipeDetailPresenterImpl(RecipeDetailView view, CompositeSubscription subscription, RxStates rxStates) {
        super(view, subscription);
        this.rxStates = rxStates;
    }

    @Override
    public void onCreate() {
        addSubscription(respondStepClick());
        addSubscription(getRecipe());
    }

    private Subscription getRecipe() {
        return rxStates.recipeSubject().subscribe(recipe -> getView().onRecipeClick(recipe));
    }

    private Subscription respondStepClick() {
        return rxStates.clickStep().subscribe(step -> getView().onStepClick(step));
    }
}
