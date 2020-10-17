package com.yychun1217.mychallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.yychun1217.mychallenge.datasource.AbstractDeliveryMergedPagingSource

class DeliveryListViewModel @ViewModelInject constructor(
    merged: AbstractDeliveryMergedPagingSource
) : ViewModel() {
    val appPage = Pager(
        PagingConfig(
            pageSize = merged.config.limit,
            prefetchDistance = 1,
            enablePlaceholders = true,
            initialLoadSize = 1
        )
    ) {
        merged
    }.flow.cachedIn(viewModelScope)
}