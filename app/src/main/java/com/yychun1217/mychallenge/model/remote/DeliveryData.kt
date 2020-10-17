package com.yychun1217.mychallenge.model.remote

data class DeliveryData(
    val id: String?,
    val remarks: String?,
    val pickupTime: String?,
    val goodsPicture: String?,
    val deliveryFee: String?,
    val surcharge: String?,
    val route: DeliveryRoute?,
    val sender: DeliverySender?
)