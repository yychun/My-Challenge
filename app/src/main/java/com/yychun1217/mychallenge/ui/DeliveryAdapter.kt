package com.yychun1217.mychallenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yychun1217.mychallenge.model.Delivery

class DeliveryAdapter(
    private val onClick: (view: View, delivery: Delivery.Ui) -> Unit
) : PagingDataAdapter<Delivery.Ui, DeliveryItemViewHolder>(ITEM_COMPARATOR) {

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        return DeliveryItemViewHolder.create(parent, onClick)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Delivery.Ui>() {
            override fun areContentsTheSame(
                oldItem: Delivery.Ui,
                newItem: Delivery.Ui
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: Delivery.Ui,
                newItem: Delivery.Ui
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
