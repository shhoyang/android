package com.hao.library.http

import androidx.annotation.StringRes
import com.hao.library.HaoLibrary
import java.lang.Exception

/**
 * @author Yang Shihao
 */
class CustomException(message: String) : Exception(message) {

    constructor(@StringRes resId: Int) : this(HaoLibrary.context.getString(resId))
}