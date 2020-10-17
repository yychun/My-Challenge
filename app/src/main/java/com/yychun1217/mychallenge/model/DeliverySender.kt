package com.yychun1217.mychallenge.model

import com.yychun1217.pagination.model.IEntity

interface DeliverySender {
    data class Api(
        val phone: String,
        val name: String,
        val email: String
    ) : IEntity.Api
}