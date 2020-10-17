package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.model.remote.DeliveryData
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Config,
    local: ILocalPagingSource<GetDeliveryRequest, DeliveryData>,
    remote: IRemotePagingSource<GetDeliveryRequest, DeliveryData>
) : AbstractDeliveryMergedPagingSource(config, local, remote) {
    companion object {
        data class Config(
            val offset: Int,
            val limit: Int
        )
    }
}