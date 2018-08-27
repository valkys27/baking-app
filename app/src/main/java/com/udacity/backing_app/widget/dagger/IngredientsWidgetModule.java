package com.udacity.backing_app.widget.dagger;

import android.content.*;
import com.udacity.backing_app.core.model.RecipeModel;
import com.udacity.backing_app.core.rx.*;
import com.udacity.backing_app.widget.helper.*;
import com.udacity.backing_app.widget.presenter.*;
import com.udacity.backing_app.widget.view.*;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class IngredientsWidgetModule {

    @Binds
    abstract IngredientsView bindIngredientsView(IngredientsWidgetProvider widgetProvider);

    @Provides
    static IngredientsPresenter provideIngredientsPresenter(IngredientsView view, IngredientsWidgetHelper helper, RecipeModel model,
                                                            CompositeSubscription subscription, RxSchedulers rxSchedulers, RxStates rxStates) {
        return new IngredientsPresenterImpl(view, helper, model, subscription, rxSchedulers, rxStates);
    }

    @Provides
    static IngredientsWidgetHelper provideHelper(SharedPreferences sharedPreferences) {
        return new IngredientsWidgetHelperImpl(sharedPreferences);
    }

    @Binds
    abstract ConfigWidgetView bindConfigWidgetView(ConfigWidgetActivity activity);

    @Provides
    static ConfigWidgetPresenter provideConfigWidgetPresenter(ConfigWidgetView view, IngredientsWidgetHelper helper, RecipeModel model,
                                                              CompositeSubscription subscription, RxSchedulers rxSchedulers) {
        return new ConfigWidgetPresenterImpl(view, model, helper, subscription, rxSchedulers);
    }
}
