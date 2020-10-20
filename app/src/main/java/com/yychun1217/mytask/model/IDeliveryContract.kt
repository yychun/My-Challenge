package com.yychun1217.mytask.model

import androidx.room.*

interface IDeliveryContract {
    @Entity(
        tableName = Db.NAME_DB_TABLE,
        indices = [
            Index(value = [Db.COLUMN_REMOTE_ID], unique = true),
        ]
    )
    data class Db(
        @ColumnInfo(name = COLUMN_DELIVERY_FEE) val fee: Float,
        @ColumnInfo(name = COLUMN_GOODS_PICTURE) val goodsPicture: String,
        @ColumnInfo(name = COLUMN_IS_FAVOURITE) val isFavourite: Boolean,
        @ColumnInfo(name = COLUMN_REMARKS) val remarks: String,
        @ColumnInfo(name = COLUMN_REMOTE_ID) val remoteId: String,
        @ColumnInfo(name = COLUMN_SURCHARGE) val surcharge: Float,
        @ColumnInfo(name = COLUMN_ROUTE_ID) val routeId: Long = 0,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    ) {
        companion object {
            const val NAME_DB_TABLE = "delivery"
            const val COLUMN_ID = "${NAME_DB_TABLE}_id"
            const val COLUMN_DELIVERY_FEE = "${NAME_DB_TABLE}_fee"
            const val COLUMN_GOODS_PICTURE = "${NAME_DB_TABLE}_goods_picture"
            const val COLUMN_IS_FAVOURITE = "${NAME_DB_TABLE}_is_favourite"
            const val COLUMN_REMARKS = "${NAME_DB_TABLE}_remarks"
            const val COLUMN_REMOTE_ID = "${NAME_DB_TABLE}_remote_id"
            const val COLUMN_ROUTE_ID = "${NAME_DB_TABLE}_route_id"
            const val COLUMN_SURCHARGE = "${NAME_DB_TABLE}_surcharge"
        }
    }

    data class Ui(
        val fee: Float,
        val goodsPicture: String,
        val isFavourite: Boolean,
        val remarks: String,
        val remoteId: String,
        val surcharge: Float,
        val _idDb: Long = 0
    )
}