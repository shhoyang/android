package com.hao.library.view.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * @author Yang Shihao
 */
interface SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}