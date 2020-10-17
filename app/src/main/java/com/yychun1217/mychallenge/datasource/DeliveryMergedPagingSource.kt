package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.model.Delivery
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, Delivery.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, Delivery.Api>
) : AbstractDeliveryMergedPagingSource(config, local, remote)