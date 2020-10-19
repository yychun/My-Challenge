package com.yychun1217.mychallenge.datasource.local

import android.database.sqlite.SQLiteException
import com.yychun1217.mychallenge.db.MiscDao
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract

class DeliveryLocalDataSource(
    private val miscDao: MiscDao
) : IDeliveryLocalDataSource {
    override suspend fun getDeliveryAndRoute(id: String): IDeliveryAndRouteContract.Db? = miscDao.get(id)

    override suspend fun update(delivery: IDeliveryAndRouteContract.Db): Boolean = try {
        miscDao.update(delivery)
        true
    } catch (e: SQLiteException) {
        false
    }
}