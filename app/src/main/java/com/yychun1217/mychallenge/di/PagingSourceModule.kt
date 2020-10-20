package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.db.DeliveryAndRouteDao
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.pagingsource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mychallenge.pagingsource.DeliveryMergedPagingSource
import com.yychun1217.mychallenge.pagingsource.remote.DeliveryRemotePagingSource
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.pagingsource.local.DeliveryAndRouteLocalPagingSource
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