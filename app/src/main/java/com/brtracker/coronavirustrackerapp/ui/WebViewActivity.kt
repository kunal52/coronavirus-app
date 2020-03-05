package com.brtracker.coronavirustrackerapp.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.util.NEWS_URL
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView.webViewClient= WebViewClient()
        webView.webChromeClient= WebChromeClient()
        val url = intent.getStringExtra(NEWS_URL);
        webView.loadUrl(url)
    }


    class WebViewClientImpl : WebViewClient()
    {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return false
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
