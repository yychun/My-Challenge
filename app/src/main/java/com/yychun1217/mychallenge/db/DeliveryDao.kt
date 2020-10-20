package com.yychun1217.mychallenge.db

import androidx.room.*
import com.yychun1217.mychallenge.model.IDeliveryContract

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE}")
    suspend fun getAll(): List<IDeliveryContract.Db>

    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE} LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<IDeliveryContract.Db>

    @Query("SELECT * FROM ${IDeliveryContract.Db.NAME_DB_TABLE} where ${IDeliveryContract.Db.COLUMN_ID} = :id")
    suspend fun get(id: Long): IDeliveryContract.Db?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg delivery: IDeliveryContract.Db): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg delivery: IDeliveryContract.Db): Int
}