package com.hao.sample

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.annotation.Inject
import com.hao.library.extensions.init
import com.hao.library.ui.BaseActivity
import com.hao.sample.adapter.MainAdapter
import com.hao.sample.databinding.ActivityMainBinding
import com.hao.sample.viewmodel.MainVM

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun initView() {
        viewBinding {
            baseToolbar.showBack(false)
            baseToolbar.setTitleText("HaoLibrary")
            baseRecyclerView.init(mainAdapter)
        }
    }

    override fun initData() {
        viewModel {
            liveData.observe(this@MainActivity) {
                mainAdapter.resetData(it)
            }

            loadData()
        }
    }
}