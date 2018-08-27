package com.udacity.backing_app.core.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.udacity.backing_app.core.App;
import com.udacity.backing_app.core.api.RecipeApi;
import com.udacity.backing_app.core.model.*;
import dagger.*;

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindApp(App app);

    @Provides
    static SharedPreferences provideSharedPreferences(App app) {
        return app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides
    static RecipeModel provideModel(RecipeApi recipeApi, Context context){
        return new RecipeModelImpl(recipeApi, context);
    }
}
