package com.udacity.backing_app.core.rx;

import com.udacity.backing_app.core.pojo.*;
import rx.subjects.*;

public class RxStates {

    public static BehaviorSubject<Recipe> RECIPE_SUBJECT = BehaviorSubject.create();
    public static PublishSubject<Recipe> CLICK_RECIPE = PublishSubject.create();
    public static PublishSubject<Step> CLICK_STEP = PublishSubject.create();
    public static BehaviorSubject<Step> STEP_SUBJECT = BehaviorSubject.create();
    public static BehaviorSubject<Integer> STEP_POSITION = BehaviorSubject.create();

    public BehaviorSubject<Recipe> recipeSubject() {
        return RECIPE_SUBJECT;
    }

    public PublishSubject<Recipe> clickRecipe() {
        return CLICK_RECIPE;
    }

    public PublishSubject<Step> clickStep() {
        return CLICK_STEP;
    }

    public BehaviorSubject<Step> stepSubject() {
        return STEP_SUBJECT;
    }

    public BehaviorSubject<Integer> stepPosition() {
        return STEP_POSITION;
    }
}
