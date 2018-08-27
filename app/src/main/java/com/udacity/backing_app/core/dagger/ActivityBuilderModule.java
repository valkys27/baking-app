package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.recipeDetail.dagger.DetailActivityModule;
import com.udacity.backing_app.recipeDetail.view.DetailActivity;
import com.udacity.backing_app.recipeList.dagger.MainListActivityModule;
import com.udacity.backing_app.recipeList.view.MainListActivity;
import com.udacity.backing_app.widget.dagger.IngredientsWidgetModule;
import com.udacity.backing_app.widget.view.ConfigWidgetActivity;
import dagger.*;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainListActivityModule.class)
    abstract MainListActivity bindMainListActivity();

    @ContributesAndroidInjector(modules = {DetailActivityModule.class, FragmentBuilderModule.class})
    abstract DetailActivity bindDetailActivity();

    @ContributesAndroidInjector(modules = IngredientsWidgetModule.class)
    abstract ConfigWidgetActivity bindConfigWidgetActivity();
}
