package com.udacity.backing_app.core.pojo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Step implements Serializable {

    @Expose
    private int id;
    @Expose
    private String shortDescription;
    @Expose
    private String description;
    @Expose
    private String videoURL;
    @Expose
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
