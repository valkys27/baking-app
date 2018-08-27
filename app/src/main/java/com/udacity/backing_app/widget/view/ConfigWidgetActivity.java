package com.udacity.backing_app.widget.view;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.*;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.utils.*;
import com.udacity.backing_app.core.view.BaseActivity;
import com.udacity.backing_app.widget.presenter.ConfigWidgetPresenter;

import java.util.List;

public class ConfigWidgetActivity extends BaseActivity<ConfigWidgetPresenter> implements ConfigWidgetView, AdapterView.OnItemSelectedListener {

    @BindView(R.id.widget_recipe_list_s)
    Spinner recipeListSpinner;

    private SpinnerAdapter adapter;
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_config_widget;
    }

    @Override
    protected void init() {
        setResult(RESULT_CANCELED);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                finish();
            }
        }
    }

    @Override
    public void swapAdapter(List<Recipe> recipeList) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, recipeList);
        recipeListSpinner.setOnItemSelectedListener(this);
        recipeListSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        view.setTag(adapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.widget_ok_b)
    void onOkButtonClicked() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Recipe recipe = (Recipe)  recipeListSpinner.getSelectedView().getTag();
        ViewUtils.updateIngredientWidget(appWidgetManager, appWidgetId, recipe.getName(), RecipeUtils.prepareIngredients(recipe.getIngredients()));
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}
