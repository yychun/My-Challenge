package com.yychun1217.mychallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yychun1217.mychallenge.model.IDeliveryRouteContract

@Dao
interface DeliveryRouteDao {
    @Query("SELECT * FROM ${IDeliveryRouteContract.Db.NAME_DB_TABLE} where ${IDeliveryRouteContract.Db.COLUMN_ID} = :id")
    suspend fun get(id: Long): IDeliveryRouteContract.Db?

    @Insert
    suspend fun insert(route: IDeliveryRouteContract.Db)

    @Insert
    suspend fun insertAll(vararg routes: IDeliveryRouteContract.Db)

    @Update
    suspend fun update(route: IDeliveryRouteContract.Db)
}