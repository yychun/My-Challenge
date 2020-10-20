package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.db.DeliveryAndRouteDao
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

class DeliveryAndRouteLocalDataSource(
    private val deliveryAndRouteDao: DeliveryAndRouteDao
) : IDeliveryAndRouteLocalDataSource {
    override suspend fun getDeliveryAndRoute(routeId: Long): IDeliveryAndRouteContract.Db? = deliveryAndRouteDao.get(routeId)
}