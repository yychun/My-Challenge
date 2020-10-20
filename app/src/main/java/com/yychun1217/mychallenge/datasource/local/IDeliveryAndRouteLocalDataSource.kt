package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

interface IDeliveryAndRouteLocalDataSource : ILocalDataSource<IDeliveryAndRouteContract.Db> {
    suspend fun getDeliveryAndRoute(routeId: Long): IDeliveryAndRouteContract.Db?
}