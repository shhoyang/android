package com.hao.library.ui

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.hao.library.R
import com.hao.library.adapter.BaseNormalAdapter
import com.hao.library.adapter.listener.OnItemClickListener
import com.hao.library.annotation.Base
import com.hao.library.annotation.Inject
import com.hao.library.view.EmptyView
import com.hao.library.view.RefreshLayout

/**
 * @author Yang Shihao
 */
@Base
abstract class BaseNormalListActivity<VB : ViewBinding, D, VM : ViewModel, A : BaseNormalAdapter<*, D>> :
    BaseActivity<VB, VM>(),
    OnItemClickListener<D> {

    private var refreshLayout: RefreshLayout? = null
    private var emptyView: EmptyView? = null

    @Inject
    lateinit var adapter: A

    @CallSuper
    override fun initView() {
        this.refreshLayout = f(R.id.baseRefreshLayout)
        this.emptyView = f(R.id.baseEmptyView)
        val recyclerView: RecyclerView = f(R.id.baseRecyclerView)!!
        registerDataObserver()
        adapter.setOnItemClickListener(this)
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = adapter
        this.refreshLayout?.setOnRefreshListener {
            onRefresh()
        }
    }

    private fun registerDataObserver() {
        if (null == emptyView) {
            return
        }
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                if (adapter.data.isEmpty()) {
                    emptyView?.noData()
                } else {
                    emptyView?.dismiss()
                }
            }
        })
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }

    open fun onRefresh() {

    }

    override fun itemClicked(view: View, item: D, position: Int) {

    }
}