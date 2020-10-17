package com.yychun1217.pagination.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import com.yychun1217.pagination.databinding.ViewNetworkStateItemBinding

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemViewHolder(
    private val binding: ViewNetworkStateItemBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup, retryCallback: () -> Unit) : this(
        ViewNetworkStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retryCallback
    )

    init {
        binding.paginationRetryButton.setOnClickListener {
            retryCallback.invoke()
        }
    }

    fun bindTo(loadState: LoadState) {
        binding.paginationProgressBar.isVisible = loadState is Loading
        binding.paginationRetryButton.isVisible = loadState is Error
        binding.paginationErrorMsg.isVisible =
            !(loadState as? Error)?.error?.message.isNullOrBlank()
        binding.paginationErrorMsg.text = (loadState as? Error)?.error?.message
    }
}
