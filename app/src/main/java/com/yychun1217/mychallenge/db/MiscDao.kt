package com.yychun1217.mychallenge.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.IRouteContract

@Dao
abstract class MiscDao(
    private val database: MyDatabase
) {
    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE}")
    abstract suspend fun getAll(): List<IDeliveryAndRouteContract.Db>

    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} where ${IRouteContract.Db.COLUMN_ID} = :routeId")
    abstract suspend fun get(routeId: String): IDeliveryAndRouteContract.Db

    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} LIMIT :limit OFFSET :offset")
    abstract suspend fun getAll(offset: Int, limit: Int): List<IDeliveryAndRouteContract.Db>

    suspend fun insert(vararg deliveryAndRoute: IDeliveryAndRouteContract.Db) {
        val deliveryDao = database.deliveryDao()
        val routeDao = database.routeDao()
        val deliveries = deliveryAndRoute.mapNotNull {
            val routeId = routeDao.insert(it.route).firstOrNull()
            Log.d("Testing", "route: ${it.route}")
            Log.d("Testing", "routeId: $routeId")
            if (routeId != null) it.delivery.copy(routeId = routeId) else null
        }
        deliveryDao.insert(*deliveries.toTypedArray())
    }

    suspend fun update(vararg deliveryAndRoute: IDeliveryAndRouteContract.Db) {
        val deliveryDao = database.deliveryDao()
        val routeDao = database.routeDao()
        deliveryDao.update(*deliveryAndRoute.map { it.delivery }.toTypedArray())
    }
}