package com.yychun1217.mychallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yychun1217.pagination.model.IEntityContract

interface IRouteContract {
    data class Api(
        val start: String,
        val end: String
    ) : IEntityContract.Api<Db, Ui> {
        override fun toDb(): Db? = Db(from = start, to = end)
        override fun toUi(): Ui? = Ui(from = start, to = end)
    }

    @Entity(tableName = Db.NAME_DB_TABLE)
    data class Db(
        @ColumnInfo(name = COLUMN_FROM) val from: String,
        @ColumnInfo(name = COLUMN_TO) val to: String,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    ) : IEntityContract.Db<Api, Ui> {
        companion object {
            const val NAME_DB_TABLE = "route"
            const val COLUMN_ID = "${NAME_DB_TABLE}_id"
            const val COLUMN_FROM = "${NAME_DB_TABLE}_from"
            const val COLUMN_TO = "${NAME_DB_TABLE}_to"
        }

        override fun toUi(): Ui? = Ui(from = from, to = to, id)
    }

    data class Ui(
        val from: String,
        val to: String,
        val _idDB: Long = 0
    ) : IEntityContract.Ui<Api, Db>
}