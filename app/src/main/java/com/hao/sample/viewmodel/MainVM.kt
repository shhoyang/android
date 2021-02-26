package com.hao.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hao.library.viewmodel.BaseViewModel

/**
 * @author Yang Shihao
 */
class MainVM : BaseViewModel() {

    val liveData = MutableLiveData<ArrayList<String>>()

    fun loadData() {
        liveData.value = arrayListOf("Hello World!")
    }
}