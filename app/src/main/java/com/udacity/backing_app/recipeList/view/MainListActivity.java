package com.udacity.backing_app.recipeList.view;

import android.content.Intent;
import android.support.v7.widget.*;
import butterknife.BindBool;
import butterknife.BindView;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.utils.ViewUtils;
import com.udacity.backing_app.core.view.BaseActivity;
import com.udacity.backing_app.recipeDetail.view.DetailActivity;
import com.udacity.backing_app.recipeList.adapter.RecipeListAdapter;
import com.udacity.backing_app.recipeList.presenter.RecipeListPresenter;

import javax.inject.Inject;
import java.util.List;

public class MainListActivity extends BaseActivity<RecipeListPresenter> implements RecipeListView {

    private static final int LAYOUT_ID = R.layout.activity_main_list;

    @Inject
    RecipeListAdapter mRecipeListAdapter;

    @BindView(R.id.recipe_list_rv)
    RecyclerView mRecipeListView;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;

    @Override
    protected void init() {
        int columns = (twoPaneMode) ? 3 : 1;
        ViewUtils.initRecyclerView(mRecipeListView, mRecipeListAdapter, columns);
    }

    @Override
    public void swapAdapter(List<Recipe> recipes) {
        mRecipeListAdapter.setData(recipes);
    }

    @Override
    public int getLayoutId() {
        return LAYOUT_ID;
    }

    @Override
    public void goToDetailActivity(Recipe recipe) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}