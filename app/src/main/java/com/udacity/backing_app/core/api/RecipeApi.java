package com.udacity.backing_app.core.api;

import com.udacity.backing_app.core.pojo.Recipe;
import retrofit2.http.GET;
import rx.Observable;

import java.util.List;

public interface RecipeApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getList();
}
