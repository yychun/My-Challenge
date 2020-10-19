package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

interface IDeliveryLocalDataSource : ILocalDataSource<IDeliveryAndRouteContract.Db> {
    suspend fun getDeliveryAndRoute(id: String): IDeliveryAndRouteContract.Db?
    suspend fun update(delivery: IDeliveryAndRouteContract.Db): Boolean
}