package com.yychun1217.mychallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yychun1217.pagination.model.EntityType
import com.yychun1217.pagination.model.IEntityContract

interface IDeliveryContract {
    data class Api(
        val deliveryFee: String,
        val goodsPicture: String,
        val id: String,
        val pickupTime: String,
        val remarks: String,
        val route: DeliveryRoute.Api,
        val sender: DeliverySender.Api,
        val surcharge: String,
    ) : IEntityContract.Api {
        @Suppress("UNCHECKED_CAST")
        override fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? =
            when (type) {
                EntityType.UI -> Ui(
                    deliveryFee = deliveryFee.substring(1).toFloat(),
                    from = route.start,
                    id = id,
                    isFavourite = false,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge.substring(1).toFloat(),
                    to = route.end
                ) as ENTITY
                EntityType.DB -> Db(
                    deliveryFee = deliveryFee.substring(1).toFloat(),
                    from = route.start,
                    id = id,
                    isFavourite = false,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge.substring(1).toFloat(),
                    to = route.end
                ) as ENTITY
                else -> super.toEntity(type)
            }
    }

    data class Ui(
        val deliveryFee: Float,
        val from: String,
        val id: String,
        val isFavourite: Boolean,
        val remarks: String,
        val goodsPicture: String,
        val surcharge: Float,
        val to: String,
    ) : IEntityContract.Ui {
        val price: Float
            get() = deliveryFee + surcharge

        override fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? {
            return when (type) {
                EntityType.DB -> Db(
                    id = id,
                    deliveryFee = deliveryFee,
                    from = from,
                    isFavourite = isFavourite,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge,
                    to = to
                ) as ENTITY
                else -> super.toEntity(type)
            }
        }
    }

    @Entity(tableName = Db.NAME_DB_TABLE)
    data class Db(
        @ColumnInfo(name = COLUMN_ID) @PrimaryKey val id: String,
        @ColumnInfo(name = COLUMN_DELIVERY_FEE) val deliveryFee: Float,
        @ColumnInfo(name = COLUMN_ROUTE_FROM) val from: String,
        @ColumnInfo(name = COLUMN_IS_FAVOURITE) val isFavourite: Boolean,
        @ColumnInfo(name = COLUMN_REMARKS) val remarks: String,
        @ColumnInfo(name = COLUMN_GOODS_PICTURE) val goodsPicture: String,
        @ColumnInfo(name = COLUMN_SURCHARGE) val surcharge: Float,
        @ColumnInfo(name = COLUMN_ROUTE_TO) val to: String,
    ) : IEntityContract.Db {
        companion object {
            const val NAME_DB_TABLE = "Delivery"

            const val COLUMN_ID = "id"
            const val COLUMN_DELIVERY_FEE = "delivery_fee"
            const val COLUMN_ROUTE_FROM = "route_from"
            const val COLUMN_IS_FAVOURITE = "is_favourite"
            const val COLUMN_REMARKS = "remarks"
            const val COLUMN_GOODS_PICTURE = "goods_picture"
            const val COLUMN_SURCHARGE = "surcharge"
            const val COLUMN_ROUTE_TO = "route_to"
        }

        @Suppress("UNCHECKED_CAST")
        override fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? =
            when (type) {
                EntityType.UI -> Ui(
                    deliveryFee = deliveryFee,
                    from = from,
                    id = id,
                    isFavourite = isFavourite,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge,
                    to = to
                ) as ENTITY
                else -> super.toEntity(type)
            }
    }
}