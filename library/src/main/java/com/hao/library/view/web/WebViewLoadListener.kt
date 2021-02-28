package com.hao.library.view.web

/**
 * @author Yang Shihao
 */
interface WebViewLoadListener {

    fun pageFinished()

    fun pageLoadError()

    fun progressChanged(newProgress: Int)
}