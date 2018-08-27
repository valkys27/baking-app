package com.udacity.backing_app.core.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.v7.widget.*;
import android.widget.RemoteViews;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.App;

public class ViewUtils {

    public static void initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, int columns) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView.getContext(), columns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private static RecyclerView.LayoutManager getLayoutManager(Context context, int columns) {
        if (columns == 0)
            throw new IllegalArgumentException();
        return (columns == 1) ? new LinearLayoutManager(context) : new GridLayoutManager(context, columns);
    }

    public static void updateIngredientWidget(AppWidgetManager appWidgetManager, int appWidgetId, String recipeName, String ingredients) {
        RemoteViews remoteViews = new RemoteViews(App.PACKAGE_NAME, R.layout.widget_ingredients);
        remoteViews.setTextViewText(R.id.widget_recipe_name_tv, recipeName);
        remoteViews.setTextViewText(R.id.widget_ingredients_tv, ingredients);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}
