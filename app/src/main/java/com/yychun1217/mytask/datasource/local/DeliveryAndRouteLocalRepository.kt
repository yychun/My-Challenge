package com.yychun1217.mytask.datasource.local

import com.yychun1217.mytask.model.IDeliveryAndRouteContract

class DeliveryAndRouteLocalRepository(
    private val iDeliveryAndRouteLocalDataSource: IDeliveryAndRouteLocalDataSource
) : IDeliveryAndRouteLocalRepository {
    override suspend fun getDeliveryAndRoute(routeId: Long): IDeliveryAndRouteContract.Db? =
        iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(routeId)
}