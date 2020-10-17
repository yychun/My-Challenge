package com.yychun1217.mychallenge.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.model.Delivery
import kotlinx.android.synthetic.main.delivery_list_item.view.*

class DeliveryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(position: Int, data: Delivery.Ui) {
        with(itemView) {
            Glide.with(context).load(data.goodsPicture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(image_goods)
            text_from.text = context.getString(R.string.delivery_list_from, data.from)
            text_to.text = context.getString(R.string.delivery_list_to, data.to)
            text_price.text = context.getString(R.string.delivery_list_price, data.price)
            val favouriteRes =
                if (data.isFavourite) android.R.drawable.star_on else android.R.drawable.star_off
            image_favourite.setImageResource(favouriteRes)
        }
    }

    companion object {
        fun create(parent: ViewGroup): DeliveryItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.delivery_list_item, parent, false)
            return DeliveryItemViewHolder(view)
        }
    }
}