package com.yychun1217.mytask.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.model.IRouteContract
import timber.log.Timber

@Dao
abstract class DeliveryAndRouteDao(
    private val database: MyDatabase
) {
    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE}")
    abstract suspend fun getAll(): List<IDeliveryAndRouteContract.Db>

    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} LIMIT :limit OFFSET :offset")
    abstract suspend fun getAll(offset: Int, limit: Int): List<IDeliveryAndRouteContract.Db>

    @Transaction
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} where ${IRouteContract.Db.COLUMN_ID} = :routeId")
    abstract suspend fun get(routeId: Long): IDeliveryAndRouteContract.Db

    suspend fun insert(vararg deliveryAndRoute: IDeliveryAndRouteContract.Db): List<Long> {
        val deliveryDao = database.deliveryDao()
        val routeDao = database.routeDao()
        val routes = deliveryAndRoute.map { it.route }
        val routesDb = routeDao.getAll()
        routesDb.withIndex().forEach {
            Timber.d("routesDb.${it.index}: ${it.value}")
        }
        routes.withIndex().forEach {
            Timber.d("routes.${it.index}: ${it.value}")
        }
        val routeResults = routeDao.insert(*routes.toTypedArray())
        Timber.d("insert.routeResults: $routeResults")
        val routeIds = routeResults.withIndex().map {
            if (it.value != -1L) it.value else {
                val route = routes[it.index]
                routeDao.getRouteId(route.from, route.to)
            }
        }
        Timber.d("insert.routeIds: $routeIds")
        val deliveriesToInsert = deliveryAndRoute.withIndex().map {
            it.value.delivery.copy(routeId = routeIds[it.index])
        }
        val deliveryResults = deliveryDao.insert(*deliveriesToInsert.toTypedArray())
        Timber.d("insert.deliveryResults: $deliveryResults")
        return routeIds
    }

    suspend fun update(vararg deliveryAndRoute: IDeliveryAndRouteContract.Db): Int {
        val deliveryDao = database.deliveryDao()
        val routeDao = database.routeDao()
        val routes = deliveryAndRoute.map { it.route }
        val routeUpdateResult = routeDao.update(*routes.toTypedArray())
        Timber.d("update.routeUpdateResult: $routeUpdateResult")
        val deliveries = deliveryAndRoute.map { it.delivery }
        val deliveryUpdateResult = deliveryDao.update(*deliveries.toTypedArray())
        Timber.d("update.deliveryUpdateResult: $deliveryUpdateResult")
        return routeUpdateResult
    }
}