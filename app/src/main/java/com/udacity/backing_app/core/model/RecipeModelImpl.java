package com.udacity.backing_app.core.model;

import android.content.Context;
import com.udacity.backing_app.core.api.RecipeApi;
import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.utils.NetworkUtils;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class RecipeModelImpl implements RecipeModel {

    private Context context;
    private RecipeApi recipeApi;

    @Inject
    public RecipeModelImpl(RecipeApi recipeApi, Context context) {
        this.recipeApi = recipeApi;
        this.context = context;
    }

    @Override
    public Observable<List<Recipe>> getList() {
        return recipeApi.getList();
    }

    @Override
    public Observable<Recipe> findRecipeById(int id) {
        return recipeApi.getList().flatMapIterable(x -> x).filter(e -> e.getId() == id);
    }

    @Override
    public Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }
}
