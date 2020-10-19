package com.yychun1217.mychallenge.viewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yychun1217.mychallenge.datasource.local.IDeliveryLocalRepository
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import kotlinx.coroutines.launch
import timber.log.Timber

class DeliveryDetailViewModel @ViewModelInject constructor(
    private val iDeliveryLocalRepository: IDeliveryLocalRepository
) : ViewModel() {
    private val _delivery: MutableLiveData<IDeliveryAndRouteContract.Ui> = MutableLiveData()
    val delivery: LiveData<IDeliveryAndRouteContract.Ui>
        get() = _delivery

    fun getDeliveryByID(id: String) {
        viewModelScope.launch {
            iDeliveryLocalRepository.getDeliveryAndRoute(id)?.toUi()?.let {
                postDelivery(it)
            }
        }
    }

    fun toggleFavourite() {
        _delivery.value?.let { old ->
            val update = old.copy(isFavourite = !old.isFavourite)
            postDelivery(update)

            viewModelScope.launch {
                update.toDb()?.let { delivery ->
                    try {
                        if (!iDeliveryLocalRepository.update(delivery)) postDelivery(old)
                    } catch (e: SQLiteConstraintException) {
                        Timber.e(e)
                        postDelivery(old)
                    }
                }
            }
        }
    }

    private fun postDelivery(delivery: IDeliveryAndRouteContract.Ui) {
        _delivery.postValue(delivery)
    }
}