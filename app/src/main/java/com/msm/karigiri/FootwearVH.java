package com.msm.karigiri;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootwearVH extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private Spinner spinner;
    private Button acbutton;

    private FirebaseFirestore firestore;

    public FootwearVH(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.footwear_img);
        acbutton=itemView.findViewById(R.id.footwear_ac);
        spinner=itemView.findViewById(R.id.size_selector);
    }

    public void bindData(final Map<String,Object> data, Context c){
        Glide.with(c).load(data.get("img")).into(imageView);
        final List<String> spinnerArray=new ArrayList<>();
        spinnerArray.add("8");
        spinnerArray.add("9");
        spinnerArray.add("10");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        acbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore=FirebaseFirestore.getInstance();
                Map<String,Object> data1=new HashMap<>();
                data1.put("desc","Size:"+spinnerArray.get(spinner.getSelectedItemPosition()));
                data1.put("fid",data.get("fid"));
                data1.put("img",data.get("img"));
                firestore.collection("Users").document(CommonData.userData.get("uid").toString()).collection("Cart").document(data.get("fid").toString()).set(data1);
            }
        });
    }
}
