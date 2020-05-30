package com.msm.karigiri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartList;
    private LinearLayoutManager linearLayoutManager;

    private CartAdapter cartAdapter;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList=findViewById(R.id.cart_list);
        linearLayoutManager=new LinearLayoutManager(this);
        cartList.setLayoutManager(linearLayoutManager);
        cartList.setHasFixedSize(true);

        cartAdapter=new CartAdapter(this);
        cartList.setAdapter(cartAdapter);

        firestore=FirebaseFirestore.getInstance();
        firestore.collection("Users").document(CommonData.userData.get("uid").toString()).collection("Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    List<DocumentSnapshot> list=task.getResult().getDocuments();
                    Log.i("12345",String.valueOf(list.size()));
                    List<Map<String,Object>> dataList=new ArrayList<>();
                    for(int i=0;i<list.size();i++){
                        dataList.add(list.get(i).getData());
                    }
                    cartAdapter.setData(dataList);
                }
            }
        });
    }
}
