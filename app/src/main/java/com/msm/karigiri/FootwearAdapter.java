package com.msm.karigiri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FootwearAdapter extends RecyclerView.Adapter<FootwearVH> {

    private List<Map<String,Object>> data=new ArrayList<>();
    private Context context;

    FootwearAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public FootwearVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.footwear_item,parent,false);
        return new FootwearVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FootwearVH holder, int position) {
        holder.bindData(data.get(position),context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Map<String, Object>> data) {
        Log.i("123",String.valueOf(data.size()));
        this.data = data;
        notifyDataSetChanged();
    }
}
