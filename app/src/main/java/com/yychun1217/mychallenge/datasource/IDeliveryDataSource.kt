package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.GetDeliveriesResponse

interface IDeliveryDataSource : IDataSource {
    suspend fun getDeliveries(offset: Int, limit: Int): GetDeliveriesResponse?
}