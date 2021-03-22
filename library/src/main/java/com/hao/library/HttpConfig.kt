package com.hao.library

import com.hao.library.http.HttpResponseModel

/**
 * @author Yang Shihao
 *
 * 网络相关
 */
interface HttpConfig {
    fun getBaseUrl(): String
    fun <T : HttpResponseModel<*>> handleResponse(t: T): Boolean
    fun isLogin(): Boolean
    fun login()
}