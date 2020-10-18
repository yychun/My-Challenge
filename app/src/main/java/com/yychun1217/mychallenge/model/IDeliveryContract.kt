package com.yychun1217.mychallenge.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
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
        val route: IDeliveryRouteContract.Api,
        val sender: IDeliverySenderContract.Api,
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
                EntityType.DB -> (route.toEntity(EntityType.DB) as? IDeliveryRouteContract.Db)?.let {
                    Db(
                        deliveryFee = deliveryFee.substring(1).toFloat(),
                        route = it,
                        id = id,
                        isFavourite = false,
                        remarks = remarks,
                        goodsPicture = goodsPicture,
                        surcharge = surcharge.substring(1).toFloat(),
                    ) as ENTITY
                }
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
                    route = IDeliveryRouteContract.Db(from, to),
                    isFavourite = isFavourite,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge,
                ) as ENTITY
                else -> super.toEntity(type)
            }
        }
    }

    @Entity(tableName = Db.NAME_DB_TABLE)
    data class Db(
        @PrimaryKey
        @ColumnInfo(name = COLUMN_ID) val id: String,
        @ColumnInfo(name = COLUMN_DELIVERY_FEE) val deliveryFee: Float,
        @Embedded val route: IDeliveryRouteContract.Db,
        @ColumnInfo(name = COLUMN_IS_FAVOURITE) val isFavourite: Boolean,
        @ColumnInfo(name = COLUMN_REMARKS) val remarks: String,
        @ColumnInfo(name = COLUMN_GOODS_PICTURE) val goodsPicture: String,
        @ColumnInfo(name = COLUMN_SURCHARGE) val surcharge: Float,
    ) : IEntityContract.Db {
        companion object {
            const val NAME_DB_TABLE = "delivery"
            const val COLUMN_ID = "${NAME_DB_TABLE}_id"
            const val COLUMN_DELIVERY_FEE = "delivery_fee"
            const val COLUMN_IS_FAVOURITE = "is_favourite"
            const val COLUMN_REMARKS = "remarks"
            const val COLUMN_GOODS_PICTURE = "goods_picture"
            const val COLUMN_SURCHARGE = "surcharge"
        }

        @Suppress("UNCHECKED_CAST")
        override fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? =
            when (type) {
                EntityType.UI -> Ui(
                    deliveryFee = deliveryFee,
                    from = route.from,
                    id = id,
                    isFavourite = isFavourite,
                    remarks = remarks,
                    goodsPicture = goodsPicture,
                    surcharge = surcharge,
                    to = route.to
                ) as ENTITY
                else -> super.toEntity(type)
            }
    }
}