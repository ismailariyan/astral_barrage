package com.example.fortest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PPTActivity extends AppCompatActivity {



  WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptactivity);
        webView=findViewById(R.id.weather);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Configure WebView to open links in the app itself
        webView.setWebViewClient(new WebViewClient());

        // Load the GLB file from a local or remote source
        loadGLBFile();


    }
    private void loadGLBFile() {
        // Load a local GLB file (replace with your GLB file's path)
        // Example for loading a GLB file from assets:
        // webView.loadUrl("file:///android_asset/your_model.glb");

        // Load a remote GLB file (replace with the URL of your GLB file)
        webView.loadUrl("https://mars.nasa.gov/layout/embed/image/mslweather/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
    }


}
