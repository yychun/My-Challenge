package com.yychun1217.mychallenge.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.databinding.ViewDeliveryListItemBinding
import com.yychun1217.mychallenge.model.Delivery

class DeliveryItemViewHolder(
    private val binding: ViewDeliveryListItemBinding,
    onClick: (view: View, delivery: Delivery.Ui) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            (it.tag as? Delivery.Ui)?.let { delivery ->
                onClick.invoke(it, delivery)
            }
        }
    }

    fun bind(position: Int, data: Delivery.Ui) {
        with(itemView.context) {
            Glide.with(this).load(data.goodsPicture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(binding.imageGoods)
            binding.textFrom.text = getString(R.string.delivery_list_from, data.from)
            binding.textTo.text = getString(R.string.delivery_list_to, data.to)
            binding.textPrice.text = getString(R.string.delivery_list_price, data.price)
            val favouriteRes =
                if (data.isFavourite) android.R.drawable.star_on else android.R.drawable.star_off
            binding.imageFavourite.setImageResource(favouriteRes)
        }
        itemView.tag = data
    }

    companion object {
        fun create(parent: ViewGroup, onClick: (view: View, delivery: Delivery.Ui) -> Unit): DeliveryItemViewHolder {
            return DeliveryItemViewHolder(
                ViewDeliveryListItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ), onClick
            )
        }
    }
}