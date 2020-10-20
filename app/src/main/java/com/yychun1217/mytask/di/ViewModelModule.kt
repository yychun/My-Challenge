package com.yychun1217.mytask.di

import com.yychun1217.mytask.datasource.local.IDeliveryAndRouteLocalRepository
import com.yychun1217.mytask.datasource.local.IDeliveryLocalRepository
import com.yychun1217.mytask.pagingsource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mytask.viewmodel.DeliveryDetailViewModel
import com.yychun1217.mytask.viewmodel.DeliveryListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@Module
@InstallIn(FragmentComponent::class)
class ViewModelModule {
    @Provides
    @FragmentScoped
    @Inject
    fun provideDeliveryListViewModel(
        merged: AbstractDeliveryMergedPagingSource
    ) = DeliveryListViewModel(merged)

    @Provides
    @FragmentScoped
    @Inject
    fun provideDeliveryDetailViewModel(
        iDeliveryLocalRepository: IDeliveryLocalRepository,
        iDeliveryAndRouteLocalRepository: IDeliveryAndRouteLocalRepository
    ) = DeliveryDetailViewModel(iDeliveryLocalRepository, iDeliveryAndRouteLocalRepository)
}