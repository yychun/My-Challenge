package com.yychun1217.mychallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yychun1217.pagination.model.EntityType
import com.yychun1217.pagination.model.IEntityContract

interface IDeliveryRouteContract {
    data class Api(
        val start: String,
        val end: String
    ) : IEntityContract.Api {
        override fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? =
            when (type) {
                EntityType.DB -> Db(start, end) as ENTITY
                else -> super.toEntity(type)
            }
    }

    @Entity(tableName = Db.NAME_DB_TABLE)
    data class Db(
        @ColumnInfo(name = COLUMN_FROM) val from: String,
        @ColumnInfo(name = COLUMN_TO) val to: String,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    ) : IEntityContract.Db {
        companion object {
            const val NAME_DB_TABLE = "route"
            const val COLUMN_ID = "${NAME_DB_TABLE}_id"
            const val COLUMN_FROM = "from"
            const val COLUMN_TO = "to"
        }
    }
}