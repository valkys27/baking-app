package com.udacity.backing_app.recipeDetail.view;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import butterknife.*;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.pojo.*;
import com.udacity.backing_app.core.view.BaseActivity;
import com.udacity.backing_app.recipeDetail.adapter.FragmentPagerAdapter;
import com.udacity.backing_app.recipeDetail.presenter.RecipeDetailPresenter;
import com.udacity.backing_app.recipePage.view.RecipePageFragment;
import com.udacity.backing_app.recipeStepDetail.view.StepDetailFragment;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity<RecipeDetailPresenter> implements RecipeDetailView {

    private static boolean isStepSelected = false;

    @Inject
    FragmentPagerAdapter adapter;

    @Nullable
    @BindView(R.id.previous_step_b)
    Button previousStepButton;

    @Nullable
    @BindView(R.id.next_step_b)
    Button nextStepButton;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;

    @BindBool(R.bool.fullscreen_video)
    boolean fullscreenVideo;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof StepDetailFragment) {
            getSupportActionBar().show();
            isStepSelected = false;
            getSupportFragmentManager().popBackStackImmediate(StepDetailFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            if (!twoPaneMode) {
                hideButtons();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        setTitle(recipe.getName());
        adapter.setData(recipe.getSteps());
        if (isStepSelected || twoPaneMode) {
            if (fullscreenVideo)
                hideButtons();
            return;
        }
        hideButtons();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RecipePageFragment(), RecipePageFragment.TAG)
                .commit();
    }

    @Optional
    @OnClick(R.id.previous_step_b)
    void onPreviousStepClick() {
        adapter.goToPrevious();
    }

    @Optional
    @OnClick(R.id.next_step_b)
    void onNextStepClick() {
        adapter.goToNext();
    }

    @Override
    public void onStepClick(Step step) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new StepDetailFragment(), StepDetailFragment.TAG)
                .addToBackStack(StepDetailFragment.TAG)
                .commit();
        if (!twoPaneMode) {
            setTitle(step.getShortDescription());
            if (fullscreenVideo) {
                getSupportActionBar().hide();
                hideButtons();
            }
            else {
                getSupportActionBar().show();
                setButtonsByAdapterPosition();
            }
        }
        isStepSelected = true;
    }

    private void hideButtons() {
        setButtonsByAdapterPosition(View.GONE);
    }

    private void showButtons() {
        setButtonsByAdapterPosition(View.VISIBLE);
    }

    private void setButtonsByAdapterPosition(int visibility) {
        previousStepButton.setVisibility(visibility);
        nextStepButton.setVisibility(visibility);
    }

    private void setButtonsByAdapterPosition() {
        if (adapter.isOnFirstPosition())
            previousStepButton.setVisibility(View.INVISIBLE);
        else
            previousStepButton.setVisibility(View.VISIBLE);
        if (adapter.isOnLastPosition())
            nextStepButton.setVisibility(View.INVISIBLE);
        else
            nextStepButton.setVisibility(View.VISIBLE);
    }
}
