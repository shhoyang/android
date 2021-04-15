package com.hao.sample

import android.app.Application
import com.hao.library.HaoLibraryConfig
import com.hao.library.HttpConfig
import com.hao.library.http.HttpResponseModel

/**
 * @author Yang Shihao
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        HaoLibraryConfig.Builder(this)
            .setHttpConfig(MyHttpConfig())
            .apply()
    }

    class MyHttpConfig : HttpConfig {

        override fun getBaseUrl(): String {
            return "http://"
        }

        override fun <T : HttpResponseModel<*>> handleResponse(t: T): Boolean {
            return false
        }

        override fun isLogin(): Boolean {
            return false
        }

        override fun login() {

        }
    }
}