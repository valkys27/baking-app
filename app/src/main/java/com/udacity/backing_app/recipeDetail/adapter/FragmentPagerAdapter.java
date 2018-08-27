package com.udacity.backing_app.recipeDetail.adapter;

import com.udacity.backing_app.core.pojo.Step;
import com.udacity.backing_app.core.rx.RxStates;

import javax.inject.Inject;
import java.util.*;

public class FragmentPagerAdapter {

    private final RxStates rxStates;

    private List<Step> steps;

    @Inject
    public FragmentPagerAdapter(RxStates rxStates) {
        this.rxStates = rxStates;
        this.steps = Collections.emptyList();
    }

    public void setData(List<Step> steps) {
        this.steps = steps;
    }

    public boolean isOnFirstPosition() {
        int position = rxStates.stepPosition().getValue();
        return position == 0;
    }

    public boolean isOnLastPosition() {
        int position = rxStates.stepPosition().getValue();
        return position == getCount() - 1;
    }

    public void goToPrevious() {
        if (isOnFirstPosition())
            return;
        int position = rxStates.stepPosition().getValue();
        select(position - 1);
    }

    private void select(int position) {
        Step step = steps.get(position);
        rxStates.stepPosition().onNext(position);
        rxStates.stepSubject().onNext(step);
        rxStates.clickStep().onNext(step);
    }

    public void goToNext() {
        if (isOnLastPosition())
            return;
        int position = rxStates.stepPosition().getValue();
        select(position + 1);
    }

    public int getCount() {
        return steps.size();
    }
}
