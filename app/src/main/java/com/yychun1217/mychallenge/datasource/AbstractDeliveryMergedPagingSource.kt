package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.model.remote.DeliveryData
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource
import com.yychun1217.pagination.pagingsource.AbstractMergedPagingSource
import kotlin.math.max

abstract class AbstractDeliveryMergedPagingSource(
    val config: DeliveryMergedPagingSource.Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, DeliveryData>,
    remote: IRemotePagingSource<GetDeliveryRequest, DeliveryData>
) : AbstractMergedPagingSource<GetDeliveryRequest, DeliveryData>(local, remote) {

    override fun getFirstKey(): GetDeliveryRequest = GetDeliveryRequest(
        config.offset,
        config.limit
    )

    override fun getPrevKey(key: GetDeliveryRequest): GetDeliveryRequest? =
        key.takeIf { it.offset > 0 }?.copy(offset = max(key.offset - key.limit, 0))

    override fun getNextKey(key: GetDeliveryRequest): GetDeliveryRequest? =
        key.copy(offset = key.offset + key.limit)
}