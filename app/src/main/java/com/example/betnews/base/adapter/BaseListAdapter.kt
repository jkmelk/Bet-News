package com.example.betnews.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.betnews.framgment.news.adapter.DiffUtilsItemCallBack

abstract class BaseListAdapter<D : Any>(diffCallback: DiffUtil.ItemCallback<D> = DiffUtilsItemCallBack()) :
    ListAdapter<D, BaseViewHolderWithoutViewHolder>(diffCallback) {

    abstract val layoutResId: Int

    abstract fun onBind(itemView: View, item: D, position: Int)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolderWithoutViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val viewHolder = BaseViewHolderWithoutViewHolder(itemView)
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(currentList[adapterPosition])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolderWithoutViewHolder, position: Int) {
        onBind(holder.itemView, currentList[position], position)
    }

    open fun onItemClick(item: D) {

    }
}

class BaseViewHolderWithoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)