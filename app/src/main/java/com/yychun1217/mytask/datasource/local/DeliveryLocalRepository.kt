package com.yychun1217.mytask.datasource.local

import com.yychun1217.mytask.model.IDeliveryContract

class DeliveryLocalRepository(
    private val iDeliveryLocalDataSource: IDeliveryLocalDataSource
) : IDeliveryLocalRepository {
    override suspend fun update(vararg delivery: IDeliveryContract.Db): Int =
        iDeliveryLocalDataSource.update(*delivery)
}