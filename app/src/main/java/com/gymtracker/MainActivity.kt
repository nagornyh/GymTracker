package com.gymtracker

import android.content.Intent
import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    inner class MailBridge {
        @JavascriptInterface
        fun sendEmail(to: String, subject: String, body: String) {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }

            try {
                // Prefer Gmail when available to keep UX predictable.
                val gmailIntent = Intent(emailIntent).apply {
                    setPackage("com.google.android.gm")
                }
                startActivity(gmailIntent)
            } catch (_: ActivityNotFoundException) {
                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email"))
                } catch (_: ActivityNotFoundException) {
                    // No email app installed; keep user in app without crashing.
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dark status bar
        window.statusBarColor = Color.parseColor("#1e1e1e")

        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.allowFileAccess = true
            settings.setSupportZoom(false)
            addJavascriptInterface(MailBridge(), "AndroidBridge")

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    val uri = request.url
                    return if (uri.scheme == "mailto") {
                        val sendIntent = Intent(Intent.ACTION_SENDTO, uri)
                        val chooser = Intent.createChooser(sendIntent, "Enviar email")
                        try {
                            startActivity(chooser)
                        } catch (_: ActivityNotFoundException) {
                            // No email app installed; keep user in app without crashing.
                        }
                        true
                    } else {
                        false
                    }
                }
            }
            webChromeClient = WebChromeClient()

            loadUrl("file:///android_asset/index.html")
        }

        setContentView(webView)
    }

    @Deprecated("Use OnBackPressedDispatcher")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}
