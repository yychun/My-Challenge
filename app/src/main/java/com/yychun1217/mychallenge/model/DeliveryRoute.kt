package com.yychun1217.mychallenge.model

import com.yychun1217.pagination.model.IEntity

interface DeliveryRoute {
    data class Api(
        val start: String,
        val end: String
    ) : IEntity.Api
}