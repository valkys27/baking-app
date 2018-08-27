package com.udacity.backing_app.core.utils;

import com.udacity.backing_app.core.pojo.Ingredient;

import java.util.List;
import java.util.Locale;

public class RecipeUtils {

    public static String prepareIngredients(List<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients)
            sb.append(String.format(Locale.ENGLISH, "- %s (%1.1f %s)\n",
                    ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getMeasure()));
        return sb.substring(0, sb.length() - 1);
    }
}
