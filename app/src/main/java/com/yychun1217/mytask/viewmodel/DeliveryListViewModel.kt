package com.yychun1217.mytask.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yychun1217.mytask.datasource.local.IDeliveryAndRouteLocalDataSource
import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.pagingsource.AbstractDeliveryMergedPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DeliveryListViewModel @ViewModelInject constructor(
    private val merged: AbstractDeliveryMergedPagingSource,
    private val iDeliveryAndRouteLocalDataSource: IDeliveryAndRouteLocalDataSource
) : ViewModel() {
    val deliverAndRoutes: Flow<PagingData<IDeliveryAndRouteContract.Ui>>
        get() = Pager(
            PagingConfig(
                pageSize = merged.config.limit,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 1
            )
        ) {
            merged
        }.flow.cachedIn(viewModelScope)

    var prevScrollPosition: Int? = null

    fun clearLocalDataSource() {
        viewModelScope.launch {
            iDeliveryAndRouteLocalDataSource.delete()
        }
    }
}