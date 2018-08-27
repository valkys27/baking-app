package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.recipePage.dagger.RecipePageFragmentModule;
import com.udacity.backing_app.recipePage.view.RecipePageFragment;
import com.udacity.backing_app.recipeStepDetail.dagger.StepDetailFragmentModule;
import com.udacity.backing_app.recipeStepDetail.view.StepDetailFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = RecipePageFragmentModule.class)
    abstract RecipePageFragment bindIngredientsFragment();

    @ContributesAndroidInjector(modules = StepDetailFragmentModule.class)
    abstract StepDetailFragment bindStepVideoFragment();
}
