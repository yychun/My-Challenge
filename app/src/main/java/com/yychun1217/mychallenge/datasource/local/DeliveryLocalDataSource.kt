package com.yychun1217.mychallenge.datasource.local

import android.database.sqlite.SQLiteException
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.model.IDeliveryContract
import timber.log.Timber

class DeliveryLocalDataSource(
    private val deliveryDao: DeliveryDao
) : IDeliveryLocalDataSource {
    override suspend fun update(vararg delivery: IDeliveryContract.Db): Int = try {
        deliveryDao.update(*delivery)
    } catch (e: SQLiteException) {
        Timber.e(e)
        0
    }
}