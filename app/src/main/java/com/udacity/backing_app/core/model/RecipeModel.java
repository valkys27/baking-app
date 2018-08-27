package com.udacity.backing_app.core.model;

import com.udacity.backing_app.core.pojo.Recipe;
import rx.Observable;

import java.util.List;

public interface RecipeModel {
    Observable<List<Recipe>> getList();
    Observable<Recipe> findRecipeById(int id);
    Observable<Boolean> isNetworkAvailable();
}
