package com.yychun1217.mychallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yychun1217.mychallenge.model.Delivery

@Database(entities = [Delivery.Db::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao

    companion object {
        const val NAME = "my-database"
    }
}