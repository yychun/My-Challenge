package com.yychun1217.mychallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.IDeliveryRouteContract

@Database(
    entities = [
        IDeliveryContract.Db::class,
        IDeliveryRouteContract.Db::class,
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao
    abstract fun deliveryRouteDao(): DeliveryRouteDao

    companion object {
        const val NAME = "my-database"
    }
}