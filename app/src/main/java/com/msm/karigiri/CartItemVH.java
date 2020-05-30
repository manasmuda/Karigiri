package com.msm.karigiri;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemVH extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private Button deleteButton;
    private TextView descView;

    private FirebaseFirestore firestore;

    public CartItemVH(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.cart_img);
        deleteButton=itemView.findViewById(R.id.cart_delete);
        descView=itemView.findViewById(R.id.cart_desc);
    }

    public void bindData(final Map<String,Object> data, Context c,final OnDataChange onDataChange, final int position){
        Glide.with(c).load(data.get("img")).into(imageView);
        descView.setText(data.get("desc").toString());
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore=FirebaseFirestore.getInstance();
                firestore.collection("Users").document(CommonData.userData.get("uid").toString()).collection("Cart").document(data.get("fid").toString()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        onDataChange.dataChange(position);
                    }
                });
            }
        });
    }


}
