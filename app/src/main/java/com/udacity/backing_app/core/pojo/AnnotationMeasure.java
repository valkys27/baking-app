package com.udacity.backing_app.core.pojo;

import android.support.annotation.StringDef;

import java.lang.annotation.*;

public class AnnotationMeasure {

    public static final String G = "G";
    public static final String K = "K";
    public static final String OZ = "OZ";
    public static final String TSP = "TSP";
    public static final String TBLSP = "TBLSP";
    public static final String UNIT = "UNIT";
    public static final String CUP = "CUP";

    @StringDef({G, K, OZ, TSP, TBLSP, UNIT, CUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Measure {
    }
}
