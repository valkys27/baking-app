package com.udacity.backing_app.recipePage.view;

import com.udacity.backing_app.core.pojo.Step;
import com.udacity.backing_app.core.view.MvpView;

import java.util.List;

public interface RecipePageView extends MvpView {
    void setIngredients(String ingredients);
    void swapAdapter(List<Step> steps);
}
