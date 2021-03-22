package com.hao.library

import android.app.Application
import com.hao.library.extensions.notNullSingleValue

/**
 * @author Yang Shihao
 */

object HaoLibrary {

    var context by notNullSingleValue<Application>()
    var CONFIG by notNullSingleValue<HaoLibraryConfig>()

    fun init(libraryConfig: HaoLibraryConfig) {
        this.CONFIG = libraryConfig
        this.context = libraryConfig.application
    }
}