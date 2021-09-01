package br.com.heiderlopes.webviewjsaction

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.Toast
import br.com.heiderlopes.webviewjsaction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("file:///android_asset/main.html")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.addJavascriptInterface(JsWebInterface(this), "androidApp")
    }

    class JsWebInterface(val context: Context) {
        @JavascriptInterface
        fun makeToast(message: String?, lengthLong: Boolean) {
            Toast.makeText(
                context,
                message,
                if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            ).show()
        }
    }
}