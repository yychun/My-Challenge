package com.yychun1217.mytask.datasource.local

import com.yychun1217.mytask.db.DeliveryAndRouteDao
import com.yychun1217.mytask.model.IDeliveryAndRouteContract

class DeliveryAndRouteLocalDataSource(
    private val deliveryAndRouteDao: DeliveryAndRouteDao
) : IDeliveryAndRouteLocalDataSource {
    override suspend fun getDeliveryAndRoute(routeId: Long): IDeliveryAndRouteContract.Db? = deliveryAndRouteDao.get(routeId)
}