package com.example.fortest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {

    private Button signupbtn;
    TextView alreadyHaveAccount;
    EditText name,email,password,confrmPassword;
    FirebaseFirestore firestore;

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUserr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confrmPassword=findViewById(R.id.conpassword);
        signupbtn=findViewById(R.id.siognupbtnbtn);
        alreadyHaveAccount=findViewById(R.id.alreadyhaveAccount);
        firebaseAuth=FirebaseAuth.getInstance();
        currentUserr=firebaseAuth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();


        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signupActivity.this, loginActivity.class));
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cheackInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cheackInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cheackInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confrmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cheackInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheackemailAndpassword();

            }
        });
    }

    private void cheackInput()
    {
        if(!TextUtils.isEmpty(name.getText()))
        {
            if(!TextUtils.isEmpty(email.getText()))
            {
                if(!TextUtils.isEmpty(password.getText()))
                {
                    if(!TextUtils.isEmpty(confrmPassword.getText()))
                    {

                        signupbtn.setEnabled(true);
                        signupbtn.setBackgroundColor(getResources().getColor(R.color.green));


                    }else {
                        signupbtn.setEnabled(false);
                        signupbtn.setBackgroundColor(getResources().getColor(R.color.gray));
                    }

                }else
                {
                    signupbtn.setEnabled(false);
                    signupbtn.setBackgroundColor(getResources().getColor(R.color.gray));
                }

                // signinbtn.setBackgroundColor(getContext().getResources().getColor(R.color.md_green_800));
            }else
            {
                //  signinprogress.setVisibility(View.GONE);
                signupbtn.setEnabled(false);
                signupbtn.setBackgroundColor(getResources().getColor(R.color.gray));

            }
        }else
        {
            signupbtn.setEnabled(false);
            signupbtn.setBackgroundColor(getResources().getColor(R.color.gray));

        }
    }

    private void cheackemailAndpassword() {

        if (password.getText().toString().matches(confrmPassword.getText().toString())) {

            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Map<String, Object> userData = new HashMap<>();
                                userData.put("User", name.getText().toString());
                                userData.put("name", email.getText().toString());
                                userData.put("uID",firebaseAuth.getUid());

                                firestore.collection("USER").document(firebaseAuth.getUid())
                                        .set(userData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Map<String, Object> userPostData = new HashMap<>();
                                                    userPostData.put("initialField", "initialValue"); // Add initial data if needed
                                                    firestore.collection("USER").document(firebaseAuth.getUid())
                                                            .collection("userpost")
                                                            .add(userPostData)  // Use `add` to create a new document with auto-generated ID
                                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                                    if (task.isSuccessful()) {
                                                                        startActivity(new Intent(signupActivity.this, HomeActivity.class));
                                                                        finish();
                                                                    }
                                                                }
                                                            });
                                                }
                                            }
                                        });

                            } else {
                                // Handle sign-up failure
                            }
                        }
                    });

        } else {
            Toast.makeText(signupActivity.this, "password does not match", Toast.LENGTH_SHORT).show();
        }
    }


}
