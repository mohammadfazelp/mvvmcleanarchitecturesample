package com.example.mvvmcleanarchitecturesample.ui.base;

import android.content.Context;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context context;
    protected ArrayList<T> items;

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val, int position);

    public BaseRvAdapter(Context context, ArrayList<T> items) {
        this.context = context;
        this.items = items;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return setViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(ArrayList<T> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }
}
