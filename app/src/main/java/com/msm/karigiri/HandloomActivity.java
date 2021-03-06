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

public class HandloomActivity extends AppCompatActivity {

    private RecyclerView handloomList;
    private LinearLayoutManager linearLayoutManager;

    private HandloomAdapter handloomAdapter;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handloom);

        handloomList=findViewById(R.id.handloom_p_list);
        linearLayoutManager=new LinearLayoutManager(this);
        handloomList.setLayoutManager(linearLayoutManager);
        handloomList.setHasFixedSize(true);

        handloomAdapter=new HandloomAdapter(this);
        handloomList.setAdapter(handloomAdapter);

        firestore= FirebaseFirestore.getInstance();
        firestore.collection("HANDLOOMS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    List<DocumentSnapshot> list=task.getResult().getDocuments();
                    Log.i("12345",String.valueOf(list.size()));
                    List<Map<String,Object>> dataList=new ArrayList<>();
                    for(int i=0;i<list.size();i++){
                        dataList.add(list.get(i).getData());
                    }
                    handloomAdapter.setData(dataList);
                }
            }
        });
    }
}
