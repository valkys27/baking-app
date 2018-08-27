package com.udacity.backing_app.widget.view;

import com.udacity.backing_app.core.view.MvpView;

public interface IngredientsView extends MvpView {
    void setText(int appWidgetId, String recipeName, String ingredients);
}
