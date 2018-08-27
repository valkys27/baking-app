package com.udacity.backing_app.core.pojo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private List<Ingredient> ingredients;
    @Expose
    private List<Step> steps;
    @Expose
    private int servings;
    @Expose
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name;
    }
}
