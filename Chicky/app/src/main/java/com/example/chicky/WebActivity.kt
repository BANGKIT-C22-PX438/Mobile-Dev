package com.example.chicky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebActivity : AppCompatActivity() {
    companion object{
        val URL = "HELLO"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val webview = findViewById<WebView>(R.id.web)
        val link = intent.getStringExtra(URL)
        link?.let { webview.loadUrl(it) }
    }
}