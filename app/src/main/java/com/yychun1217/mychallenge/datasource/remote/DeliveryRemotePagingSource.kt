package com.yychun1217.mychallenge.datasource.remote

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.model.remote.DeliveryData
import com.yychun1217.pagination.datasource.IRemotePagingSource

class DeliveryRemotePagingSource(
    private val service: DeliveryService
) : IRemotePagingSource<GetDeliveryRequest, DeliveryData> {
    override suspend fun loadPage(key: GetDeliveryRequest): List<DeliveryData>? =
        service.getDeliveries(key.offset, key.limit)
}