package com.udacity.backing_app.recipePage.view;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.BindView;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.pojo.Step;
import com.udacity.backing_app.core.utils.ViewUtils;
import com.udacity.backing_app.core.view.BaseFragment;
import com.udacity.backing_app.recipePage.presenter.RecipePagePresenter;
import com.udacity.backing_app.recipePage.adapter.StepListAdapter;

import javax.inject.Inject;
import java.util.List;

public class RecipePageFragment extends BaseFragment<RecipePagePresenter> implements RecipePageView {

    public static final String TAG = RecipePageFragment.class.getSimpleName();

    @BindView(R.id.step_list_rv)
    RecyclerView mDetailItemList;

    @Inject
    StepListAdapter mStepListAdapter;

    @BindView(R.id.ingredient_list_tv)
    TextView ingredientsTv;

    @Override
    protected void init() {
        ViewUtils.initRecyclerView(mDetailItemList, mStepListAdapter, 1);
        mDetailItemList.setNestedScrollingEnabled(false);
        mDetailItemList.setFocusable(false);
    }

    @Override
    public void swapAdapter(List<Step> steps) {
        mStepListAdapter.setData(steps);
    }

    @Override
    public void setIngredients(String ingredients) {
        ingredientsTv.setText(ingredients);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recipe_page;
    }
}