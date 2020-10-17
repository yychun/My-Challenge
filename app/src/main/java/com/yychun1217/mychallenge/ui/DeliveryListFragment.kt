package com.yychun1217.mychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.yychun1217.mychallenge.databinding.FragmentDeliveryListBinding
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
import com.yychun1217.pagination.ui.PaginationLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryListFragment : Fragment() {
    @Inject
    lateinit var viewModel: DeliveryListViewModel
    private lateinit var binding: FragmentDeliveryListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryListBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.listDelivery.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val deliveryAdapter = DeliveryAdapter().apply {
                addLoadStateListener { loadState ->
                    if (loadState.refresh is LoadState.NotLoading) {
                        binding.listDelivery.visibility = View.VISIBLE
                    } else {
                        binding.listDelivery.visibility = View.GONE
                    }
                }
            }

            lifecycleScope.launchWhenResumed {
                viewModel.appPage.collectLatest {
                    deliveryAdapter.submitData(it)
                }
            }

            adapter = deliveryAdapter.withLoadStateFooter(PaginationLoadStateAdapter {
                deliveryAdapter.retry()
            })
        }
    }
}