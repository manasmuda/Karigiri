package com.msm.karigiri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandloomAdapter extends RecyclerView.Adapter<HandloomVH> {

    private List<Map<String,Object>> data=new ArrayList<>();
    private Context context;

    HandloomAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public HandloomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.handloom_item,parent,false);
        return new HandloomVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HandloomVH holder, int position) {
        holder.bindData(data.get(position),context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
