package com.yychun1217.mychallenge.model

import com.yychun1217.pagination.model.IEntityContract

interface IDeliverySenderContract {
    data class Api(
        val phone: String,
        val name: String,
        val email: String
    ) : IEntityContract.Api
}