package com.yychun1217.mytask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yychun1217.mytask.model.IDeliveryContract
import com.yychun1217.mytask.model.IRouteContract

@Database(
    entities = [
        IDeliveryContract.Db::class,
        IRouteContract.Db::class,
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao
    abstract fun routeDao(): RouteDao
    abstract fun deliveryAndRouteDao(): DeliveryAndRouteDao

    companion object {
        const val NAME = "my-database"
    }
}