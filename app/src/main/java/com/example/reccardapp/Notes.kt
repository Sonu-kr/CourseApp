package com.example.reccardapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class Notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        val webview = findViewById<WebView>(R.id.webView)
        val pdfUrl =
            "https://drive.google.com/file/d/16OGLVdaJkx-_FDIlNQC1vJh8VNKsCBYa/view?usp=share_link"
        webview.settings.javaScriptEnabled = true
        webview.settings.builtInZoomControls = true
        webview.webChromeClient = object : WebChromeClient() {}
        webview.loadUrl(pdfUrl)
    }
}