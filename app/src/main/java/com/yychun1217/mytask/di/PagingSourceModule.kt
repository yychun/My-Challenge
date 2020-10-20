package com.yychun1217.mytask.di

import com.yychun1217.mytask.DeliveryService
import com.yychun1217.mytask.db.DeliveryAndRouteDao
import com.yychun1217.mytask.model.IDeliveryAndRouteContract
import com.yychun1217.mytask.pagingsource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mytask.pagingsource.DeliveryMergedPagingSource
import com.yychun1217.mytask.pagingsource.remote.DeliveryRemotePagingSource
import com.yychun1217.mytask.model.request.GetDeliveryRequest
import com.yychun1217.mytask.pagingsource.local.DeliveryAndRouteLocalPagingSource
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import com.yychun1217.pagination.pagingsource.IRemotePagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

@Module
@InstallIn(FragmentComponent::class)
class PagingSourceModule {
    @Provides
    @Inject
    fun provideILocalPagingSource(
        deliveryAndRouteDao: DeliveryAndRouteDao
    ): ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db> =
        DeliveryAndRouteLocalPagingSource(deliveryAndRouteDao)

    @Provides
    @Inject
    fun provideIRemotePagingSource(
        service: DeliveryService
    ): IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api> =
        DeliveryRemotePagingSource(service)

    @Provides
    @Inject
    fun provideDeliveryMergedPagingSource(
        local: ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db>,
        remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Api>
    ): AbstractDeliveryMergedPagingSource = DeliveryMergedPagingSource(
        AbstractDeliveryMergedPagingSource.Companion.Config(0, 20),
        local,
        remote
    )
}