package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.model.remote.DeliveryData
import com.yychun1217.pagination.datasource.ILocalPagingSource

class DeliveryLocalPagingSource : ILocalPagingSource<GetDeliveryRequest, DeliveryData> {
    override suspend fun insert(key: GetDeliveryRequest, page: List<DeliveryData>): Boolean {
        return true
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<DeliveryData>? {
        return null
    }
}