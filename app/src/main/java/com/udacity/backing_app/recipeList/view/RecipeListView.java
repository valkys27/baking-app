package com.udacity.backing_app.recipeList.view;

import com.udacity.backing_app.core.view.MvpView;
import com.udacity.backing_app.core.pojo.Recipe;
import rx.Observable;

import java.util.List;

public interface RecipeListView extends MvpView {
    void swapAdapter(List<Recipe> recipes);
    void goToDetailActivity(Recipe recipe);
}
