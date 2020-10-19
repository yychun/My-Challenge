package com.yychun1217.mychallenge.pagingsource

import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api>
) : AbstractDeliveryMergedPagingSource(config, local, remote)