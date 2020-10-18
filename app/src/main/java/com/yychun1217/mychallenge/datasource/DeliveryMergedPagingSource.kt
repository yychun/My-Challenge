package com.yychun1217.mychallenge.datasource

import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource

class DeliveryMergedPagingSource(
    config: Companion.Config,
    local: ILocalPagingSource<GetDeliveryRequest, IDeliveryContract.Db>,
    remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryContract.Api>
) : AbstractDeliveryMergedPagingSource(config, local, remote)