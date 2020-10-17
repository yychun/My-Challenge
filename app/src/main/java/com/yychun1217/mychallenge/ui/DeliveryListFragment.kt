package com.yychun1217.mychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_delivery_list.*
import kotlinx.coroutines.flow.collectLatest
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

        initViews()
    }

    private fun initViews() {
        delivery_list.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        delivery_list.adapter = DeliveryAdapter()

        lifecycleScope.launchWhenResumed {
            viewModel.appPage.collectLatest {
                (delivery_list.adapter as PagingDataAdapter<Delivery.Ui, DeliveryItemViewHolder>).submitData(it)
            }
        }
    }
}