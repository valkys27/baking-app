package com.udacity.backing_app.widget.view;

import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.view.MvpView;

import java.util.List;

public interface ConfigWidgetView extends MvpView {
    void swapAdapter(List<Recipe> recipeList);
}
