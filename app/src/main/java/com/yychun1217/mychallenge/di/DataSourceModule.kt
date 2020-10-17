package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.DeliveryService
import com.yychun1217.mychallenge.datasource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mychallenge.datasource.local.DeliveryLocalPagingSource
import com.yychun1217.mychallenge.datasource.DeliveryMergedPagingSource
import com.yychun1217.mychallenge.datasource.remote.DeliveryRemotePagingSource
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.mychallenge.model.remote.DeliveryData
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

@Module
@InstallIn(FragmentComponent::class)
class DataSourceModule {

    @Provides
    fun provideILocalPagingSource(): ILocalPagingSource<GetDeliveryRequest, DeliveryData> =
        DeliveryLocalPagingSource()

    @Provides
    @Inject
    fun provideIRemotePagingSource(
        service: DeliveryService
    ): IRemotePagingSource<GetDeliveryRequest, DeliveryData> = DeliveryRemotePagingSource(service)

    @Provides
    @Inject
    fun provideDeliveryMergedPagingSource(
        local: ILocalPagingSource<GetDeliveryRequest, DeliveryData>,
        remote: IRemotePagingSource<GetDeliveryRequest, DeliveryData>
    ): AbstractDeliveryMergedPagingSource = DeliveryMergedPagingSource(
        DeliveryMergedPagingSource.Companion.Config(0, 20),
        local,
        remote
    )
}