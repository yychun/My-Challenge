package com.yychun1217.mychallenge.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yychun1217.mychallenge.datasource.IDeliveryDataSource
import com.yychun1217.mychallenge.model.remote.DeliveryData
import kotlinx.coroutines.launch

class DeliveryListViewModel @ViewModelInject constructor(
    private val remote: IDeliveryDataSource
) : ViewModel() {
    companion object {
        private const val GET_DELIVERIES_LIMIT = 20
    }

    private val offset: Int = 0
    private val _deliveries: MutableLiveData<List<DeliveryData>> = MutableLiveData()
    val deliveries: LiveData<List<DeliveryData>>
        get() = _deliveries

    fun loadDeliveries() {
        viewModelScope.launch {
            remote.getDeliveries(offset, GET_DELIVERIES_LIMIT)?.let {
                _deliveries.postValue(
                    (_deliveries.value ?: listOf()).plus(it)
                )
            }
        }
    }
}