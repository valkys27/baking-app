package com.udacity.backing_app.core.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.*;

import java.util.*;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final Context mContext;

    protected List<T> mData;

    public BaseAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    abstract protected int getItemLayoutId();
    abstract protected VH createViewHolder(View view);

    public void setData(List<T> data) {
        mData = new ArrayList<>(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getItemLayoutId(), parent, false);
        view.setFocusable(true);
        return createViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
