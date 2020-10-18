package com.yychun1217.mychallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yychun1217.mychallenge.model.Delivery

class DeliveryDetailViewModel : ViewModel() {
    private val _delivery: MutableLiveData<Delivery.Ui> = MutableLiveData()
    val delivery: LiveData<Delivery.Ui>
        get() = _delivery

    fun setDetail(delivery: Delivery.Ui) {
        _delivery.postValue(delivery)
    }
}