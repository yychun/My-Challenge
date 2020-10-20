package com.yychun1217.mytask.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yychun1217.mytask.datasource.local.IDeliveryAndRouteLocalRepository
import com.yychun1217.mytask.datasource.local.IDeliveryLocalRepository
import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import kotlinx.coroutines.launch
import timber.log.Timber

class DeliveryDetailViewModel @ViewModelInject constructor(
    private val iDeliveryLocalRepository: IDeliveryLocalRepository,
    private val iDeliveryAndRouteLocalRepository: IDeliveryAndRouteLocalRepository
) : ViewModel() {
    private val _deliveryAndRoute: MutableLiveData<IDeliveryAndRouteContract.Ui> = MutableLiveData()
    val deliveryAndRoute: LiveData<IDeliveryAndRouteContract.Ui>
        get() = _deliveryAndRoute

    fun getDeliveryAndRouteByRouteID(routeId: Long) {
        Timber.d("getDeliveryAndRouteByRouteId: $routeId")
        viewModelScope.launch {
            iDeliveryAndRouteLocalRepository.getDeliveryAndRoute(routeId)?.toUi()?.let {
                postDelivery(it)
            }
        }
    }

    fun toggleFavourite() {
        _deliveryAndRoute.value?.let { old ->
            val update = old.copy(delivery = old.delivery.copy(
                isFavourite = !old.delivery.isFavourite
            ))
            postDelivery(update)

            viewModelScope.launch {
                update.toDb()?.delivery?.let {
                    if (iDeliveryLocalRepository.update(it) == 0) postDelivery(old)
                }
            }
        }
    }

    private fun postDelivery(delivery: IDeliveryAndRouteContract.Ui) {
        _deliveryAndRoute.postValue(delivery)
    }
}