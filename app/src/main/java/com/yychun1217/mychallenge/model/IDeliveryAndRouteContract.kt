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
                delivery = IDeliveryContract.Ui(
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
    }

    data class Ui(
        val delivery: IDeliveryContract.Ui,
        val route: IRouteContract.Ui,
    ) : IEntityContract.Ui<Api, Db> {
        val fee: Float
            get() = delivery.fee
        val goodsPicture: String
            get() = delivery.goodsPicture
        val isFavourite: Boolean
            get() = delivery.isFavourite
        val price: Float
            get() = delivery.fee + delivery.surcharge
        val remarks: String
            get() = delivery.remarks
        val remoteId: String
            get() = delivery.remoteId
        val surcharge: Float
            get() = delivery.surcharge

        override fun toDb(): Db? = route.toDb()?.let {
            Db(
                delivery = IDeliveryContract.Db(
                    fee = delivery.fee,
                    goodsPicture = delivery.goodsPicture,
                    isFavourite = delivery.isFavourite,
                    remarks = delivery.remarks,
                    remoteId = delivery.remoteId,
                    surcharge = delivery.surcharge,
                    routeId = it.id,
                    id = delivery._idDb,
                ),
                route = it,
            )
        }
    }

    data class Db(
        @Relation(
            parentColumn = IRouteContract.Db.COLUMN_ID,
            entityColumn = IDeliveryContract.Db.COLUMN_ROUTE_ID
        )
        val delivery: IDeliveryContract.Db,
        @Embedded val route: IRouteContract.Db,
    ) : IEntityContract.Db<Api, Ui> {
        override fun toUi(): Ui? = route.toUi()?.let {
            Ui(
                delivery = IDeliveryContract.Ui(
                    fee = delivery.fee,
                    goodsPicture = delivery.goodsPicture,
                    isFavourite = delivery.isFavourite,
                    remoteId = delivery.remoteId,
                    remarks = delivery.remarks,
                    surcharge = delivery.surcharge,
                    _idDb = delivery.id,
                ),
                route = it,
            )
        }
    }
}