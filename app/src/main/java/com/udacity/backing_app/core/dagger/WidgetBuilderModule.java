package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.widget.dagger.IngredientsWidgetModule;
import com.udacity.backing_app.widget.view.IngredientsWidgetProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WidgetBuilderModule {

    @ContributesAndroidInjector(modules = IngredientsWidgetModule.class)
    abstract IngredientsWidgetProvider bindIngredientsWidgetProvider();
}
