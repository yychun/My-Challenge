package com.yychun1217.mytask.db

import androidx.room.*
import com.yychun1217.mytask.model.IRouteContract

@Dao
interface RouteDao {
    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE}")
    suspend fun getAll(): List<IRouteContract.Db>

    @Query("SELECT * FROM ${IRouteContract.Db.NAME_DB_TABLE} where ${IRouteContract.Db.COLUMN_ID} = :id")
    suspend fun get(id: Long): IRouteContract.Db?

    @Query("SELECT ${IRouteContract.Db.COLUMN_ID} FROM ${IRouteContract.Db.NAME_DB_TABLE} where ${IRouteContract.Db.COLUMN_FROM} = :from AND ${IRouteContract.Db.COLUMN_TO} = :to")
    suspend fun getRouteId(from: String, to: String): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg route: IRouteContract.Db): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg route: IRouteContract.Db): Int

    @Query("DELETE FROM ${IRouteContract.Db.NAME_DB_TABLE}")
    suspend fun delete()
}