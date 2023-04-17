package com.example.reccardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        WebView webview = findViewById(R.id.webView);
        String pdfUrl = "https://drive.google.com/file/d/16OGLVdaJkx-_FDIlNQC1vJh8VNKsCBYa/view?usp=share_link";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebChromeClient(new WebChromeClient(){

        });
        webview.loadUrl(pdfUrl);


    }
}