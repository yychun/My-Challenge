package com.yychun1217.mytask.pagingsource

import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api>
) : AbstractDeliveryMergedPagingSource(config, local, remote)