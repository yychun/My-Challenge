package com.yychun1217.mychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryListFragment : Fragment() {
    @Inject
    lateinit var viewModel: DeliveryListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.deliveries.observe(viewLifecycleOwner) {
            // update list
        }
        viewModel.loadDeliveries()
    }
}