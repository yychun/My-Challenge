package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.datasource.AbstractDeliveryMergedPagingSource
import com.yychun1217.mychallenge.viewmodel.DeliveryDetailViewModel
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
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
    fun provideDeliveryDetailViewModel() = DeliveryDetailViewModel()
}