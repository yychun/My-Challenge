package com.yychun1217.mytask.pagingsource.remote

import com.yychun1217.mytask.DeliveryService
import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryRemotePagingSource(
    private val service: DeliveryService
) : IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api> {
    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryAndRouteContract.Api> =
        service.getDeliveries(key.offset, key.limit)
}