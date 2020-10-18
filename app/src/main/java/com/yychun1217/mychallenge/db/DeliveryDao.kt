package com.yychun1217.mychallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yychun1217.mychallenge.model.Delivery

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM ${Delivery.Db.NAME_DB_TABLE}")
    suspend fun getAll(): List<Delivery.Db>

    @Query("SELECT * FROM ${Delivery.Db.NAME_DB_TABLE} LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<Delivery.Db>

    @Insert
    suspend fun insert(delivery: Delivery.Db)

    @Insert
    suspend fun insertAll(vararg deliveries: Delivery.Db)
}