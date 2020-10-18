package com.yychun1217.mychallenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yychun1217.mychallenge.model.IDeliveryContract

class DeliveryAdapter(
    private val onClick: (view: View, delivery: IDeliveryContract.Ui) -> Unit
) : PagingDataAdapter<IDeliveryContract.Ui, DeliveryItemViewHolder>(ITEM_COMPARATOR) {

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        return DeliveryItemViewHolder.create(parent, onClick)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<IDeliveryContract.Ui>() {
            override fun areContentsTheSame(
                oldItem: IDeliveryContract.Ui,
                newItem: IDeliveryContract.Ui
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: IDeliveryContract.Ui,
                newItem: IDeliveryContract.Ui
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
