package com.udacity.backing_app.recipeList.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.TextView;
import butterknife.*;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.adapter.BaseAdapter;
import com.udacity.backing_app.core.pojo.Recipe;
import com.udacity.backing_app.core.rx.RxStates;
import org.jetbrains.annotations.NotNull;


public class RecipeListAdapter extends BaseAdapter<Recipe, RecipeListAdapter.RecipeViewHolder> {

    private static final int ITEM_LAYOUT_ID = R.layout.item_recipe;

    private final RxStates mRxStates;

    public RecipeListAdapter(@NotNull Context context, RxStates rxStates) {
        super(context);
        mRxStates = rxStates;
    }

    @Override
    protected int getItemLayoutId() {
        return ITEM_LAYOUT_ID;
    }

    @Override
    protected RecipeViewHolder createViewHolder(View view) {
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int i) {
        final Recipe recipe = mData.get(i);
        holder.cardView.setTag(recipe);
        holder.recipeName.setText(recipe.getName());
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view) CardView cardView;
        @BindView(R.id.recipe_name_tv) TextView recipeName;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(v -> {
                Recipe recipe = mData.get(getAdapterPosition());
                mRxStates.recipeSubject().onNext(recipe);
                mRxStates.clickRecipe().onNext(recipe);
            });
        }
    }
}
