package com.yychun1217.mychallenge.model

import com.yychun1217.pagination.model.IEntityContract

interface DeliveryRoute {
    data class Api(
        val start: String,
        val end: String
    ) : IEntityContract.Api
}