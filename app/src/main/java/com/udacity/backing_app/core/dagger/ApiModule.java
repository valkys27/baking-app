package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.core.api.RecipeApi;
import dagger.*;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    RecipeApi provideRecipeApi(Retrofit retrofit) {
        return retrofit.create(RecipeApi.class);
    }
}
