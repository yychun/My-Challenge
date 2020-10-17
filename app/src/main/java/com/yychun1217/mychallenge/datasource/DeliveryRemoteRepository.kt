package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.GetDeliveriesResponse

class DeliveryRemoteRepository(
    private val service: DeliveryService
) : IDeliveryDataSource {
    override suspend fun getDeliveries(offset: Int, limit: Int): GetDeliveriesResponse? =
        service.getDeliveries(offset, limit)
}