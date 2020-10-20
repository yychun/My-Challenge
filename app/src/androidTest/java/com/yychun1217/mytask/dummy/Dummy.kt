package com.yychun1217.mytask.dummy

import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.model.IDeliveryContract
import com.yychun1217.mytask.model.IRouteContract

object Dummy {
    const val GOOD_PICTURES = "https://loremflickr.com/320/240/cat?lock=84911"
    const val DELIVER_ID = 123L
    const val ROUTE_ID = 999L
    const val WRONG_ROUTE_ID = 998L

    val DELIVERY_DB = IDeliveryAndRouteContract.Db(
        delivery = IDeliveryContract.Db(
            fee = 100f,
            goodsPicture = GOOD_PICTURES,
            isFavourite = false,
            id = DELIVER_ID,
            remarks = "remarks",
            remoteId = "REMOTE_ID",
            surcharge = 5.279f,
            routeId = ROUTE_ID,
        ),
        route = IRouteContract.Db(
            from = "from",
            to = "to",
            id = ROUTE_ID,
        )
    )
    val DELIVERY_DB_UPDATED =
        DELIVERY_DB.copy(delivery = DELIVERY_DB.delivery.copy(isFavourite = true))
}