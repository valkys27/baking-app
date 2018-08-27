package com.udacity.backing_app.widget.helper;

import rx.Observable;

public interface IngredientsWidgetHelper {
    Observable<Integer> getRecipeIdByWidgetId(int appWidgetId);
    Observable<Boolean> saveRecipeIdForWidget(int appWidgetId, int recipeId);
    void deleteRecipeIdForWidget(int appWidgetId);
}
