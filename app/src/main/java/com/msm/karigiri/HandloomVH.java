package com.msm.karigiri;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Map;

public class HandloomVH extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private Button acbutton;

    public HandloomVH(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.hanloom_img);
        acbutton=itemView.findViewById(R.id.handloom_ac);
    }

    public void bindData(Map<String,Object> data, Context c){
        Glide.with(c).load(data.get("img")).into(imageView);
    }
}
