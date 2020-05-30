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

public class CartAdapter extends RecyclerView.Adapter<CartItemVH> {

    private List<Map<String,Object>> data=new ArrayList<>();
    private Context context;

    private OnDataChange onDataChange;

    CartAdapter(Context context){
        this.context=context;
        onDataChange=new OnDataChange() {
            @Override
            public void dataChange(int pos) {
                data.remove(pos);
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public CartItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cart_item,parent,false);
        return new CartItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemVH holder, int position) {
        holder.bindData(data.get(position),context,onDataChange,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Map<String,Object>> data){
        this.data=data;
        notifyDataSetChanged();
    }
}
