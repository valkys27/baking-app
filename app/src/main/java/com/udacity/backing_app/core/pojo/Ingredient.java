package com.udacity.backing_app.core.pojo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import static com.udacity.backing_app.core.pojo.AnnotationMeasure.*;

public class Ingredient implements Serializable {

    @Expose
    private double quantity;
    @Expose
    @Measure
    private String measure;
    @Expose
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
