package com.hao.library

import android.app.Application
import androidx.annotation.StyleRes

/**
 * @author Yang Shihao
 */
class HaoLibraryConfig private constructor(val application: Application) {

    var toolbarLayoutTheme = R.style.ToolbarLayout
    var emptyViewTheme = R.style.EmptyView
    var confirmDialogTheme = R.style.ConfirmDialog
    var loadingDialogTheme = R.style.LoadingDialog

    lateinit var httpConfig: HttpConfig

    class Builder(application: Application) {

        private var haoLibraryConfig: HaoLibraryConfig = HaoLibraryConfig(application)

        fun setToolbarLayoutTheme(@StyleRes themeResId: Int): Builder {
            haoLibraryConfig.toolbarLayoutTheme = themeResId
            return this
        }

        fun setEmptyViewTheme(@StyleRes themeResId: Int): Builder {
            haoLibraryConfig.emptyViewTheme = themeResId
            return this
        }

        fun setConfirmDialogTheme(@StyleRes themeResId: Int): Builder {
            haoLibraryConfig.confirmDialogTheme = themeResId
            return this
        }

        fun setLoadingDialogTheme(@StyleRes themeResId: Int): Builder {
            haoLibraryConfig.loadingDialogTheme = themeResId
            return this
        }

        fun setHttpConfig(httpConfig: HttpConfig): Builder {
            haoLibraryConfig.httpConfig = httpConfig
            return this
        }

        fun apply() {
            HaoLibrary.init(haoLibraryConfig)
        }
    }
}