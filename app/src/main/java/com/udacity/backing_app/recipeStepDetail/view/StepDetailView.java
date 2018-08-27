package com.udacity.backing_app.recipeStepDetail.view;

import com.udacity.backing_app.core.pojo.Step;
import com.udacity.backing_app.core.view.MvpView;

public interface StepDetailView extends MvpView {
    void init(Step step);
}
