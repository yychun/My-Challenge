package com.yychun1217.mytask.datasource.local

import com.yychun1217.mytask.model.IDeliveryAndRouteContract

interface IDeliveryAndRouteLocalDataSource : ILocalDataSource<IDeliveryAndRouteContract.Db> {
    suspend fun getDeliveryAndRoute(routeId: Long): IDeliveryAndRouteContract.Db?
    suspend fun delete()
}