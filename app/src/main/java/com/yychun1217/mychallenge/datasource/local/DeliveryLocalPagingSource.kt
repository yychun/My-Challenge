package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.ILocalPagingSource

class DeliveryLocalPagingSource : ILocalPagingSource<GetDeliveryRequest, Delivery.Db> {
    override suspend fun insert(key: GetDeliveryRequest, page: List<Delivery.Db>): Boolean {
        return true
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<Delivery.Db>? {
        return null
    }
}