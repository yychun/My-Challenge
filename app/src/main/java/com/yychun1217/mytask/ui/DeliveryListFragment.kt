package com.yychun1217.mytask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yychun1217.mytask.R
import com.yychun1217.mytask.databinding.FragmentDeliveryListBinding
import com.yychun1217.mytask.model.ViewState
import com.yychun1217.mytask.model.toViewState
import com.yychun1217.mytask.ui.navigation.navigate
import com.yychun1217.mytask.viewmodel.DeliveryListViewModel
import com.yychun1217.pagination.ui.PaginationLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryListFragment : Fragment() {
    companion object {
        const val KEY_SCROLL_POSITION = "KEY_FIRST_VISIBLE_ITEM_POSITION"
    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val scrollPosition =
            (binding.listDelivery.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        outState.putInt(KEY_SCROLL_POSITION, scrollPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getInt(KEY_SCROLL_POSITION)?.let {
            viewModel.prevScrollPosition = it
        }
    }

    private fun restoreScrollPosition() {
        viewModel.prevScrollPosition?.let {
            binding.listDelivery.scrollToPosition(it)
            viewModel.prevScrollPosition = null
        }
    }

    private fun initViews() {
        setupNavigationUi()

        binding.listDelivery.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val deliveryAdapter = DeliveryAdapter { _, delivery ->
                navigate(
                    R.id.fragment_delivery_detail,
                    DeliveryDetailFragment.toBundle(delivery.route._idDb)
                )
            }.apply {
                addLoadStateListener { loadState ->
                    Timber.d("deliveryList.loadState: $loadState")
                    onNewViewState(loadState.toViewState())
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

            binding.buttonErrorRetry.setOnClickListener {
                deliveryAdapter.retry()
            }
        }
        onNewViewState(ViewState.Loading(true))
    }

    private fun setupNavigationUi() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_master_fragment)
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        val appBinding = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(toolbar, navController, appBinding)
    }

    private fun onNewViewState(viewState: ViewState) {
        Timber.d("deliveryList.viewState: $viewState")
        when (viewState) {
            is ViewState.Loading -> {
                binding.layoutError.visibility = View.GONE
                if (viewState.isEmpty) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textEmpty.visibility = View.GONE
                    binding.listDelivery.visibility = View.GONE
                } else {
                    binding.listDelivery.visibility = View.VISIBLE
                    binding.textEmpty.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
            }
            is ViewState.Error -> {
                binding.progressBar.visibility = View.GONE
                if (viewState.isEmpty) {
                    binding.textError.text = viewState.error.localizedMessage
                    binding.layoutError.visibility = View.VISIBLE
                    binding.textEmpty.visibility = View.GONE
                    binding.listDelivery.visibility = View.GONE
                } else {
                    binding.listDelivery.visibility = View.VISIBLE
                    binding.textEmpty.visibility = View.GONE
                    binding.layoutError.visibility = View.GONE
                }
            }
            ViewState.NotLoading -> {
                restoreScrollPosition()
                binding.listDelivery.visibility = View.VISIBLE
                binding.textEmpty.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.layoutError.visibility = View.GONE
            }
        }
    }
}