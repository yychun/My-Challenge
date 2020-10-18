package com.yychun1217.mychallenge.pagingsource.remote

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryRemotePagingSource(
    private val service: DeliveryService
) : IRemotePagingSource<GetDeliveryRequest, IDeliveryContract.Api> {
    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryContract.Api> =
        service.getDeliveries(key.offset, key.limit)
}