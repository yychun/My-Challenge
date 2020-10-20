package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.datasource.local.*
import com.yychun1217.mychallenge.db.DeliveryAndRouteDao
import com.yychun1217.mychallenge.db.DeliveryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject

@Module
@InstallIn(FragmentComponent::class)
class DataSourceModule {
    @Provides
    @Inject
    fun provideIDeliveryAndRouteLocalDataSource(
        deliveryAndRouteDao: DeliveryAndRouteDao
    ): IDeliveryAndRouteLocalDataSource = DeliveryAndRouteLocalDataSource(deliveryAndRouteDao)

    @Provides
    @Inject
    fun provideIDeliveryLocalDataSource(
        deliveryDao: DeliveryDao
    ): IDeliveryLocalDataSource = DeliveryLocalDataSource(deliveryDao)

    @Provides
    @Inject
    fun provideIDeliveryAndRouteLocalRepository(
        iDeliveryAndRouteLocalDataSource: IDeliveryAndRouteLocalDataSource
    ): IDeliveryAndRouteLocalRepository =
        DeliveryAndRouteLocalRepository(iDeliveryAndRouteLocalDataSource)

    @Provides
    @Inject
    fun provideIDeliveryLocalRepository(
        iDeliveryLocalDataSource: IDeliveryLocalDataSource
    ): IDeliveryLocalRepository = DeliveryLocalRepository(iDeliveryLocalDataSource)
}