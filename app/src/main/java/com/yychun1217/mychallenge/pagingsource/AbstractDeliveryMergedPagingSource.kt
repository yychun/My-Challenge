package com.yychun1217.mychallenge.pagingsource

import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import com.yychun1217.pagination.pagingsource.IRemotePagingSource
import com.yychun1217.pagination.pagingsource.AbstractMergedPagingSource
import kotlin.math.max

abstract class AbstractDeliveryMergedPagingSource(
    val config: Config,
    local: ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api>
) : AbstractMergedPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db, IDeliveryAndRouteContract.Api, IDeliveryAndRouteContract.Ui>(
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
        key: GetDeliveryRequest
    ): GetDeliveryRequest? = key.takeIf { it.offset > 0 }?.copy(
        offset = max(key.offset - key.limit, 0)
    )

    override fun getNextKey(
        key: GetDeliveryRequest
    ): GetDeliveryRequest? = key.copy(offset = key.offset + key.limit)
}