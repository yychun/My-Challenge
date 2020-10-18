package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yychun1217.mychallenge.databinding.FragmentDeliveryDetailBinding
import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.viewmodel.DeliveryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryDetailFragment : Fragment() {
    companion object {
        private const val KEY_DELIVERY = "delivery"
    }

    @Inject
    lateinit var viewModel: DeliveryDetailViewModel
    private lateinit var binding: FragmentDeliveryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryDetailBinding.inflate(inflater).also {
        it.viewModel = this.viewModel
        it.lifecycleOwner = viewLifecycleOwner
        this.binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.delivery.observe(viewLifecycleOwner) {
            it?.let {
                setGoodsPicture(it.goodsPicture)
            }
        }
        arguments?.getParcelable<Delivery.Ui>(KEY_DELIVERY)?.let {
            viewModel.setDetail(it)
        }
    }

    private fun setGoodsPicture(pictureUrl: String) {
        with (binding.imageDeliveryDetailGoods) {
            Glide.with(this)
                .load(pictureUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this)
        }
    }
}