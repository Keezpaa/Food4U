package no.kasperi.Ui.webView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_web_view.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.food4u.R

abstract class WebViewActivity : AbstractActivity(R.layout.activity_web_view) {

    override fun init() {}

    @SuppressLint("SetJavaScriptEnabled")
    override fun running() {
        val url = intent.getStringExtra("URL")
        val webSettings: WebSettings = oppskrift_webview.settings
        webSettings.javaScriptEnabled = true
    }

    override fun stopped() {}

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
    }
}