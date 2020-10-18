package com.yychun1217.mychallenge.dummy

import com.yychun1217.mychallenge.model.IDeliveryContract

object Dummy {
    const val GOOD_PICTURES = "https://loremflickr.com/320/240/cat?lock=84911"
    const val DELIVER_ID = "123"
    const val DELIVER_WRONG_ID = "124"

    val DELIVERY_DB = IDeliveryContract.Db(
        id = DELIVER_ID,
        deliveryFee = 100f,
        from = "from",
        isFavourite = false,
        remarks = "remarks",
        goodsPicture = GOOD_PICTURES,
        surcharge = 5.279f,
        to = "to"
    )
    val DELIVERY_DB_UPDATED = DELIVERY_DB.copy(isFavourite = true)
}