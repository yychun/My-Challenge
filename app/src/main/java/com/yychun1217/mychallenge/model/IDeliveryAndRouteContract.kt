package com.yychun1217.mychallenge.model

import androidx.room.*
import com.yychun1217.pagination.model.IEntityContract

interface IDeliveryAndRouteContract {
    data class Api(
        val deliveryFee: String,
        val goodsPicture: String,
        val id: String,
        val pickupTime: String,
        val remarks: String,
        val route: IRouteContract.Api,
        val surcharge: String,
    ) : IEntityContract.Api<Db, Ui> {
        override fun toDb(): Db? = route.toDb()?.let {
            Db(
                delivery = IDeliveryContract.Db(
                    fee = deliveryFee.substring(1).toFloat(),
                    goodsPicture = goodsPicture,
                    isFavourite = false,
                    remarks = remarks,
                    remoteId = id,
                    surcharge = surcharge.substring(1).toFloat(),
                ),
                route = it,
            )
        }

        override fun toUi(): Ui? = route.toUi()?.let {
            Ui(
                fee = deliveryFee.substring(1).toFloat(),
                route = it,
                remoteId = id,
                isFavourite = false,
                remarks = remarks,
                goodsPicture = goodsPicture,
                surcharge = surcharge.substring(1).toFloat(),
            )
        }
    }

    data class Ui(
        val fee: Float,
        val isFavourite: Boolean,
        val remarks: String,
        val remoteId: String,
        val route: IRouteContract.Ui,
        val goodsPicture: String,
        val surcharge: Float,
    ) : IEntityContract.Ui<Api, Db> {
        val price: Float
            get() = fee + surcharge

        override fun toDb(): Db? = route.toDb()?.let {
            Db(
                delivery = IDeliveryContract.Db(
                    fee = fee,
                    goodsPicture = goodsPicture,
                    isFavourite = isFavourite,
                    remarks = remarks,
                    remoteId = remoteId,
                    surcharge = surcharge
                ),
                route = it,
            )
        }
    }

    data class Db(
        @Relation(
            parentColumn = IRouteContract.Db.COLUMN_ID,
            entityColumn = IDeliveryContract.Db.COLUMN_ROUTE
        )
        val delivery: IDeliveryContract.Db,
        @Embedded val route: IRouteContract.Db,
    ) : IEntityContract.Db<Api, Ui> {
        override fun toUi(): Ui? = route.toUi()?.let {
            Ui(
                fee = delivery.fee,
                route = it,
                remoteId = delivery.remoteId,
                isFavourite = delivery.isFavourite,
                remarks = delivery.remarks,
                goodsPicture = delivery.goodsPicture,
                surcharge = delivery.surcharge,
            )
        }
    }
}