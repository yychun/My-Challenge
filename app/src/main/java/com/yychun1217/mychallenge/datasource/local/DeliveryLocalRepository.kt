package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryContract

class DeliveryLocalRepository(
    private val iDeliveryLocalDataSource: IDeliveryLocalDataSource
) : IDeliveryLocalRepository {
    override suspend fun update(vararg delivery: IDeliveryContract.Db): Int =
        iDeliveryLocalDataSource.update(*delivery)
}