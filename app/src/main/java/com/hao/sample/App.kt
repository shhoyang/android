package com.hao.sample

import android.app.Application
import com.hao.library.HaoLibrary
import com.hao.library.HaoLibraryConfig
import com.hao.library.HttpConfig
import com.hao.library.http.HttpResponseModel

/**
 * @author Yang Shihao
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        HaoLibrary.init(
            HaoLibraryConfig.Builder(this)
                .setHttpConfig(MyHttpConfig())
                .build()
        )
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