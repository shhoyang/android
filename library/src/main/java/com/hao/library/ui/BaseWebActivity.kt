package com.hao.library.ui

import android.view.WindowManager
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.hao.library.R
import com.hao.library.annotation.Base
import com.hao.library.view.web.ProgressAnimHelper
import com.hao.library.view.web.ProgressWebView
import com.hao.library.view.web.WebViewLoadListener

@Base
abstract class BaseWebActivity<VB : ViewBinding, VM : ViewModel> : BaseActivity<VB, VM>(),
    WebViewLoadListener {

    private var webView: ProgressWebView? = null
    private var progressBar: ProgressBar? = null

    private var progressAnimHelper: ProgressAnimHelper? = null

    @CallSuper
    override fun initView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)
        webView = f(R.id.baseWebView)
        progressBar = f(R.id.baseProgressBar)

        if (progressBar != null) {
            progressAnimHelper = ProgressAnimHelper(progressBar!!)
        }
        webView?.setWebViewLoadListener(this)
    }

    override fun initData() {

    }

    override fun onDestroy() {
        progressAnimHelper?.destroy()
        webView?.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (webView != null && webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun pageFinished() {
    }

    override fun pageLoadError() {

    }

    override fun progressChanged(newProgress: Int) {
        progressAnimHelper?.progressChanged(newProgress)
    }

    fun load(url: String) {
        webView?.doLoadUrl(url)
    }

    fun getWevView(): ProgressWebView? {
        return webView
    }
}

