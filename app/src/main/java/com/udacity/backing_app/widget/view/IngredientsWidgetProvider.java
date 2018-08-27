package com.udacity.backing_app.widget.view;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.utils.ViewUtils;
import com.udacity.backing_app.core.view.BaseWidgetProvider;
import com.udacity.backing_app.widget.presenter.IngredientsPresenter;

public class IngredientsWidgetProvider extends BaseWidgetProvider<IngredientsPresenter> implements IngredientsView {

    private AppWidgetManager appWidgetManager;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        this.appWidgetManager = appWidgetManager;
        for (int appWidgetId : appWidgetIds)
            getPresenter().setWidget(appWidgetId);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds)
            getPresenter().deleteWidget(appWidgetId);
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public int getLayoutId() {
        return R.layout.widget_ingredients;
    }

    @Override
    public void setText(int appWidgetId, String recipeName, String ingredients) {
        ViewUtils.updateIngredientWidget(appWidgetManager, appWidgetId, recipeName, ingredients);
    }
}

