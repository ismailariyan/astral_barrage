package com.example.fortest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class semiviewActivity extends AppCompatActivity {
    private KenBurnsView planetimg;
    private TextView Selectedplanet,radious,orbital,gravity;
    FirebaseFirestore firebaseFirestore;
    String Uid;
    String IMg;
    String Name;
    String DvIew,Giff;
    ShimmerFrameLayout shimmerFrameLayout;
    Button button,Book;
    String gi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semiview);
        planetimg=findViewById(R.id.planet);
        Selectedplanet=findViewById(R.id.selectedplanet);
        radious=findViewById(R.id.planetredious);
        orbital=findViewById(R.id.orbitalperiod);
        gravity=findViewById(R.id.gravity);
        shimmerFrameLayout=findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        button=findViewById(R.id.dview);
        Book=findViewById(R.id.book);

        Uid=getIntent().getStringExtra("UID");
        Giff=getIntent().getStringExtra("GVIEW");
        AccelerateDecelerateInterpolator di=new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator transitionGenerator=new RandomTransitionGenerator(3000,di);
        planetimg.setTransitionGenerator(transitionGenerator);

        DvIew=getIntent().getStringExtra("DVIEW");
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("package").document(Uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists())
                            {
                                String grravity=documentSnapshot.getString("gravity");
                                String raddious=documentSnapshot.getString("radious");
                                String orrbit=documentSnapshot.getString("orbit");
                                 Name=documentSnapshot.getString("name");
                                IMg=documentSnapshot.getString("i");
                                Selectedplanet.setText(Name);
                                gi =documentSnapshot.getString("g");
                                radious.setText(raddious);
                                orbital.setText(orrbit);
                                gravity.setText(grravity);

                                Glide.with(semiviewActivity.this).load(IMg).into(planetimg);


                            }
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);

                        }
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(semiviewActivity.this,MainActivity.class);
                intent.putExtra("DVIEw",DvIew);
                intent.putExtra("N",Name);
                startActivity(intent);
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(semiviewActivity.this, detailaboutplanetActivity.class);
                intent.putExtra("G",gi);
                intent.putExtra("N",Name);
                startActivity(intent);
            }
        });



    }
}