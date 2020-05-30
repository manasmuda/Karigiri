package com.msm.karigiri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEdit;
    private EditText pwdEdit;
    private EditText nameEdit;
    private Button registerButton;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseFirestore firestore;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==RESULT_OK){

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();

        emailEdit=findViewById(R.id.register_email);
        pwdEdit=findViewById(R.id.register_pwd);
        nameEdit=findViewById(R.id.register_name);
        registerButton=findViewById(R.id.register_register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(emailEdit.getText().toString(),pwdEdit.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user=task.getResult().getUser();
                            final Map<String,Object> userData=new HashMap<>();
                            userData.put("uid",user.getUid());
                            userData.put("name",nameEdit.getText().toString());
                            userData.put("email",emailEdit.getText().toString());
                            firestore.collection("Users").document(user.getUid()).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        CommonData.userData=userData;
                                        startActivityForResult(new Intent(RegisterActivity.this,Home.class),2);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
