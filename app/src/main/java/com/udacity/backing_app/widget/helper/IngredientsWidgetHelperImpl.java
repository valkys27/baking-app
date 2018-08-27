package com.udacity.backing_app.widget.helper;

import android.content.SharedPreferences;
import rx.Observable;

import javax.inject.Inject;

public class IngredientsWidgetHelperImpl implements IngredientsWidgetHelper {

    private SharedPreferences sharedPreferences;

    @Inject
    public IngredientsWidgetHelperImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<Integer> getRecipeIdByWidgetId(int appWidgetId) {
        return Observable.fromCallable(() -> {
            int recipeId = sharedPreferences.getInt(String.valueOf(appWidgetId), -1);
            if (appWidgetId == 1)
                throw new IllegalArgumentException("Recipe for app widget not found!");
            return recipeId;
        });
    }

    @Override
    public Observable<Boolean> saveRecipeIdForWidget(int appWidgetId, int recipeId) {
        return Observable.fromCallable(() -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(String.valueOf(appWidgetId), recipeId);
            editor.apply();
            return true;
        });
    }

    @Override
    public void deleteRecipeIdForWidget(int appWidgetId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(appWidgetId));
        editor.apply();
    }
}
