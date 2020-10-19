package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

class DeliveryLocalRepository(
    private val iDeliveryLocalDataSource: IDeliveryLocalDataSource
) : IDeliveryLocalRepository {
    override suspend fun getDeliveryAndRoute(id: String): IDeliveryAndRouteContract.Db? =
        iDeliveryLocalDataSource.getDeliveryAndRoute(id)

    override suspend fun update(delivery: IDeliveryAndRouteContract.Db): Boolean =
        iDeliveryLocalDataSource.update(delivery)
}