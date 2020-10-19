package com.yychun1217.mychallenge.pagingsource.remote

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryRemotePagingSource(
    private val service: DeliveryService
) : IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api> {
    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryAndRouteContract.Api> =
        service.getDeliveries(key.offset, key.limit)
}