package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryContract

class DeliveryLocalRepository(
    private val iDeliveryLocalDataSource: IDeliveryLocalDataSource
) : IDeliveryLocalRepository {
    override suspend fun getDelivery(id: String): IDeliveryContract.Db? =
        iDeliveryLocalDataSource.getDelivery(id)

    override suspend fun update(delivery: IDeliveryContract.Db): Boolean =
        iDeliveryLocalDataSource.update(delivery)
}