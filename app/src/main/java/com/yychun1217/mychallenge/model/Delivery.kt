package com.yychun1217.mychallenge.model

import com.yychun1217.pagination.model.EntityType
import com.yychun1217.pagination.model.IEntity

interface Delivery {
    data class Api(
        val deliveryFee: String,
        val goodsPicture: String,
        val id: String,
        val pickupTime: String,
        val remarks: String,
        val route: DeliveryRoute.Api,
        val sender: DeliverySender.Api,
        val surcharge: String,
    ) : IEntity.Api {
        @Suppress("UNCHECKED_CAST")
        override fun <ENTITY : IEntity<EntityType>> toEntity(type: EntityType): ENTITY? =
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
    ) : IEntity.Ui {
        val price: Float
            get() = deliveryFee + surcharge
    }

    data class Db(
        val deliveryFee: Float,
        val from: String,
        val id: String,
        val isFavourite: Boolean,
        val remarks: String,
        val goodsPicture: String,
        val surcharge: Float,
        val to: String,
    ) : IEntity.Db {
        @Suppress("UNCHECKED_CAST")
        override fun <ENTITY : IEntity<EntityType>> toEntity(type: EntityType): ENTITY? =
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