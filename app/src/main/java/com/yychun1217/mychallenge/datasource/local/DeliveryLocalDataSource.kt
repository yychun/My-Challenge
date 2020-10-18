package com.yychun1217.mychallenge.datasource.local

import android.database.sqlite.SQLiteException
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.model.IDeliveryContract

class DeliveryLocalDataSource(
    private val deliveryDao: DeliveryDao
) : IDeliveryLocalDataSource {
    override suspend fun getDelivery(id: String): IDeliveryContract.Db? = deliveryDao.get(id)

    override suspend fun update(delivery: IDeliveryContract.Db): Boolean = try {
        deliveryDao.update(delivery)
        true
    } catch (e: SQLiteException) {
        false
    }
}