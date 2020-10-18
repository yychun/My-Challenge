package com.yychun1217.mychallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yychun1217.mychallenge.model.IDeliveryContract

class DeliveryDetailViewModel(
) : ViewModel() {
    private val _delivery: MutableLiveData<IDeliveryContract.Ui> = MutableLiveData()
    val delivery: LiveData<IDeliveryContract.Ui>
        get() = _delivery

    fun setDetail(delivery: IDeliveryContract.Ui) {
        _delivery.postValue(delivery)
    }
}