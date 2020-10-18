package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var binding: FragmentDeliveryDetailBinding
    @Inject
    lateinit var viewModel: DeliveryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryDetailBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.delivery.observe(viewLifecycleOwner) {
            binding.itemDetail.text = it.toString()
        }

        arguments?.getParcelable<Delivery.Ui>(KEY_DELIVERY)?.let {
            viewModel.setDetail(it)
        }
    }
}