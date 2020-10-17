package com.yychun1217.mychallenge.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.model.Delivery
import kotlinx.android.synthetic.main.delivery_list_item.view.*

class DeliveryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(position: Int, data: Delivery.Ui) {
        itemView.id_text.setText(data.toString())
    }

    companion object {
        const val ICON_SIZE = 100
        const val ROUND_CORNER_SIZE = 25

        fun create(parent: ViewGroup): DeliveryItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.delivery_list_item, parent, false)
            return DeliveryItemViewHolder(view)
        }
    }
}