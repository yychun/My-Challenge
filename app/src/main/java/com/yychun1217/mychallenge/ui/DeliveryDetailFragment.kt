package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yychun1217.mychallenge.databinding.FragmentDeliveryDetailBinding
import com.yychun1217.mychallenge.viewmodel.DeliveryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryDetailFragment : Fragment() {
    companion object {
        private const val KEY_ID = "id"

        fun toBundle(id: String): Bundle = bundleOf(
            KEY_ID to id,
        )
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
        arguments?.getString(KEY_ID)?.let {
            viewModel.getDeliveryByID(it)
        } ?: kotlin.run {
            Timber.w("Key $KEY_ID must exist in arguments to instantiate DeliveryDetailFragment")
            findNavController().popBackStack()
        }
    }

    private fun setGoodsPicture(pictureUrl: String) {
        with(binding.imageDeliveryDetailGoods) {
            if (tag == pictureUrl) return
            Glide.with(this)
                .load(pictureUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this)
            tag = pictureUrl
        }
    }
}