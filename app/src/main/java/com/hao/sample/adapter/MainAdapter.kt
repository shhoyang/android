package com.hao.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hao.library.adapter.BaseNormalAdapter
import com.hao.library.adapter.ViewHolder
import com.hao.sample.databinding.MainItemBinding

/**
 * @author Yang Shihao
 */
class MainAdapter : BaseNormalAdapter<MainItemBinding, String>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ) = MainItemBinding.inflate(layoutInflater, parent, false)

    override fun bindViewHolder(
        viewHolder: ViewHolder<MainItemBinding>,
        item: String,
        position: Int,
        payloads: MutableList<Any>
    ) {
        viewHolder.viewBinding {
            root.text = item
        }
    }
}