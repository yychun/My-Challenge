package com.yychun1217.mychallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yychun1217.mychallenge.model.IDeliveryContract

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE}")
    suspend fun getAll(): List<IDeliveryContract.Db>

    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE} LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<IDeliveryContract.Db>

    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE} where ${IDeliveryContract.Db.COLUMN_ID} = :id")
    suspend fun get(id: String): IDeliveryContract.Db?

    @Insert
    suspend fun insert(vararg delivery: IDeliveryContract.Db)

    @Update
    suspend fun update(vararg delivery: IDeliveryContract.Db)
}