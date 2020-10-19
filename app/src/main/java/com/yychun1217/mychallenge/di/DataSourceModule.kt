package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.datasource.local.*
import com.yychun1217.mychallenge.db.MiscDao
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
    fun provideIDeliveryLocalDataSource(
        miscDao: MiscDao
    ): IDeliveryLocalDataSource = DeliveryLocalDataSource(miscDao)

    @Provides
    @Inject
    fun provideIDeliveryLocalRepository(
        iDeliveryLocalDataSource: IDeliveryLocalDataSource
    ): IDeliveryLocalRepository = DeliveryLocalRepository(iDeliveryLocalDataSource)
}