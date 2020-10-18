package com.yychun1217.mychallenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yychun1217.pagination.model.EntityType
import com.yychun1217.pagination.model.IEntityContract
import kotlinx.android.parcel.Parcelize

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

    @Parcelize
    data class Ui(
        val deliveryFee: Float,
        val from: String,
        val id: String,
        val isFavourite: Boolean,
        val remarks: String,
        val goodsPicture: String,
        val surcharge: Float,
        val to: String,
    ) : IEntityContract.Ui, Parcelable {
        val price: Float
            get() = deliveryFee + surcharge
    }

    @Entity(tableName = Db.NAME_DB_TABLE)
    data class Db(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "delivery_fee") val deliveryFee: Float,
        @ColumnInfo(name = "route_from") val from: String,
        @ColumnInfo(name = "is_favourite") val isFavourite: Boolean,
        @ColumnInfo(name = "remarks") val remarks: String,
        @ColumnInfo(name = "goods_picture") val goodsPicture: String,
        @ColumnInfo(name = "surcharge") val surcharge: Float,
        @ColumnInfo(name = "route_to") val to: String,
    ) : IEntityContract.Db {
        companion object {
            const val NAME_DB_TABLE = "Delivery"
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