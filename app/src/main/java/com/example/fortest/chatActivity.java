package com.example.fortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class chatActivity extends AppCompatActivity {
    LinearLayout layoutt;
    TextView T1,T2,T3,T4,T5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layoutt=findViewById(R.id.chat);

        T1=findViewById(R.id.t1);
        T2=findViewById(R.id.t2);
        T3=findViewById(R.id.t3);
        T4=findViewById(R.id.t4);
        T5=findViewById(R.id.t5);


        layoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutt.setVisibility(View.GONE);
                T1.setVisibility(View.VISIBLE);
                T2.setVisibility(View.VISIBLE);
                T3.setVisibility(View.VISIBLE);
                T4.setVisibility(View.VISIBLE);
                T5.setVisibility(View.VISIBLE);
            }
        });

    }
}