package com.yychun1217.mychallenge.datasource.remote

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.IRemotePagingSource

class DeliveryRemotePagingSource(
    private val service: DeliveryService
) : IRemotePagingSource<GetDeliveryRequest, Delivery.Api> {
    override suspend fun loadPage(key: GetDeliveryRequest): List<Delivery.Api> =
        service.getDeliveries(key.offset, key.limit)
}