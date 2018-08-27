package com.udacity.backing_app.recipeDetail.view;

import com.udacity.backing_app.core.pojo.*;
import com.udacity.backing_app.core.view.MvpView;

public interface RecipeDetailView extends MvpView {
    void onRecipeClick(Recipe recipe);
    void onStepClick(Step step);
}
