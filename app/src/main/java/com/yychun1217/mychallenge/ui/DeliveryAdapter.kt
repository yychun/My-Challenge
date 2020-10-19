package com.yychun1217.mychallenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

class DeliveryAdapter(
    private val onClick: (view: View, delivery: IDeliveryAndRouteContract.Ui) -> Unit
) : PagingDataAdapter<IDeliveryAndRouteContract.Ui, DeliveryItemViewHolder>(ITEM_COMPARATOR) {

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        return DeliveryItemViewHolder.create(parent, onClick)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<IDeliveryAndRouteContract.Ui>() {
            override fun areContentsTheSame(
                oldItem: IDeliveryAndRouteContract.Ui,
                newItem: IDeliveryAndRouteContract.Ui
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: IDeliveryAndRouteContract.Ui,
                newItem: IDeliveryAndRouteContract.Ui
            ): Boolean = oldItem.remoteId == newItem.remoteId
        }
    }
}
