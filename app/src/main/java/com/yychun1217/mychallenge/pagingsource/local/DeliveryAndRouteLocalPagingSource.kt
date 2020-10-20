package com.yychun1217.mychallenge.pagingsource.local

import com.yychun1217.mychallenge.db.DeliveryAndRouteDao
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import timber.log.Timber

class DeliveryAndRouteLocalPagingSource(
    private val deliveryAndRouteDao: DeliveryAndRouteDao
) : ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db> {
    override suspend fun insert(
        key: GetDeliveryRequest,
        page: List<IDeliveryAndRouteContract.Db>
    ): List<IDeliveryAndRouteContract.Db> {
        val insertResults = deliveryAndRouteDao.insert(*page.toTypedArray())
        Timber.d("insertResults: $insertResults")
        return page.withIndex().map {
            val delivery = it.value.delivery
            val route = it.value.route
            val routeId = insertResults[it.index]
            it.value.copy(delivery = delivery.copy(routeId = routeId), route = route.copy(id = routeId))
        }
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryAndRouteContract.Db> =
        deliveryAndRouteDao.getAll(key.offset, key.limit)
}