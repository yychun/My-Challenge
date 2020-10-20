package com.yychun1217.mytask

import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryService {
    @GET("/v2/deliveries")
    suspend fun getDeliveries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<IDeliveryAndRouteContract.Api>
}