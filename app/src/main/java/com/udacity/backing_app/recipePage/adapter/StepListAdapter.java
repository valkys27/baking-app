package com.udacity.backing_app.recipePage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.*;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.adapter.BaseAdapter;
import com.udacity.backing_app.core.pojo.*;
import com.udacity.backing_app.core.rx.RxStates;

import javax.inject.Inject;
import java.util.Locale;

public class StepListAdapter extends BaseAdapter<Step, StepListAdapter.DetailItemViewHolder> {

    private static final int ITEM_LAYOUT_ID = R.layout.item_step;

    private final RxStates rxStates;

    @Inject
    public StepListAdapter(Context context, RxStates rxStates) {
        super(context);
        this.rxStates = rxStates;
    }

    @Override
    protected int getItemLayoutId() {
        return ITEM_LAYOUT_ID;
    }

    @Override
    protected DetailItemViewHolder createViewHolder(View view) {
        return new DetailItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailItemViewHolder holder, int position) {
        final Step step = mData.get(position);
        String order = (step.getId() == 0) ? "" : String.format(Locale.getDefault(),"Step %d: ", step.getId());
        holder.text.setText(order.concat(step.getShortDescription()));
    }

    class DetailItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.step_short_description_tv) TextView text;

        DetailItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Step step = mData.get(position);
                rxStates.stepPosition().onNext(position);
                rxStates.stepSubject().onNext(step);
                rxStates.clickStep().onNext(step);
            });
        }
    }
}
