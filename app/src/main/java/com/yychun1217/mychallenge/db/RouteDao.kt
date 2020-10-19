package com.yychun1217.mychallenge.db

import androidx.room.*
import com.yychun1217.mychallenge.model.IRouteContract

@Dao
interface RouteDao {
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} where ${IRouteContract.Db.COLUMN_ID} = :id")
    suspend fun get(id: Long): IRouteContract.Db?

    @Insert
    suspend fun insert(vararg route: IRouteContract.Db): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(route: IRouteContract.Db)
}