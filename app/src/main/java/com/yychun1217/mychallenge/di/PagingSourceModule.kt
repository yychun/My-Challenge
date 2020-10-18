package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.pagingsource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mychallenge.pagingsource.DeliveryMergedPagingSource
import com.yychun1217.mychallenge.pagingsource.remote.DeliveryRemotePagingSource
import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.pagingsource.local.DeliveryLocalPagingSource
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
        deliveryDao: DeliveryDao
    ): ILocalPagingSource<GetDeliveryRequest, IDeliveryContract.Db> =
        DeliveryLocalPagingSource(deliveryDao)

    @Provides
    @Inject
    fun provideIRemotePagingSource(
        service: DeliveryService
    ): IRemotePagingSource<GetDeliveryRequest, IDeliveryContract.Api> =
        DeliveryRemotePagingSource(service)

    @Provides
    @Inject
    fun provideDeliveryMergedPagingSource(
        local: ILocalPagingSource<GetDeliveryRequest, IDeliveryContract.Db>,
        remote: IRemotePagingSource<GetDeliveryRequest, IDeliveryContract.Api>
    ): AbstractDeliveryMergedPagingSource = DeliveryMergedPagingSource(
        AbstractDeliveryMergedPagingSource.Companion.Config(0, 20),
        local,
        remote
    )
}