package com.example.psynessapp.feed
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.psynessapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class axochat : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_axochat, container, false)
        val webView = view.findViewById<WebView>(R.id.webView)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false // Permitir que WebView maneje la URL por defecto
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl("axocode.gerdoc.com/Psyness/zSocial/hiaxo.jsp") // Asegúrate de que la URL esté completa

        return view
    }



    companion object {
        fun newInstance(param1: String, param2: String) =
            axochat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
