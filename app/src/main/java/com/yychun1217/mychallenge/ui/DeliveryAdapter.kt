package com.yychun1217.mychallenge.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yychun1217.mychallenge.model.remote.DeliveryData

class DeliveryAdapter : PagingDataAdapter<DeliveryData, DeliveryItemViewHolder>(ITEM_COMPARATOR) {

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        return DeliveryItemViewHolder.create(parent)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<DeliveryData>() {
            override fun areContentsTheSame(
                oldItem: DeliveryData,
                newItem: DeliveryData
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: DeliveryData,
                newItem: DeliveryData
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
