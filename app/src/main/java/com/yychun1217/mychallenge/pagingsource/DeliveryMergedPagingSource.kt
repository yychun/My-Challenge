package com.yychun1217.mychallenge.pagingsource

import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import com.yychun1217.pagination.pagingsource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, IDeliveryContract.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryContract.Api>
) : AbstractDeliveryMergedPagingSource(config, local, remote)