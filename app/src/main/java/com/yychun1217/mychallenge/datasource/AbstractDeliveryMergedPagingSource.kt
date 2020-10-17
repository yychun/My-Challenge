package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource
import com.yychun1217.pagination.pagingsource.AbstractMergedPagingSource
import kotlin.math.max

abstract class AbstractDeliveryMergedPagingSource(
    val config: Config,
    local: ILocalPagingSource<GetDeliveryRequest, Delivery.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, Delivery.Api>
) : AbstractMergedPagingSource<GetDeliveryRequest, Delivery.Db, Delivery.Api, Delivery.Ui>(
    local,
    remote
) {
    companion object {
        data class Config(
            val offset: Int,
            val limit: Int
        )
    }

    override fun getFirstKey(): GetDeliveryRequest = GetDeliveryRequest(
        config.offset,
        config.limit
    )

    override fun getPrevKey(
        key: GetDeliveryRequest,
        pageSize: Int
    ): GetDeliveryRequest? = key.takeIf { it.limit == pageSize && it.offset > 0 }?.copy(
        offset = max(key.offset - key.limit, 0)
    )

    override fun getNextKey(
        key: GetDeliveryRequest,
        pageSize: Int
    ): GetDeliveryRequest? =
        key.takeIf { it.limit == pageSize }?.copy(offset = key.offset + key.limit)
}