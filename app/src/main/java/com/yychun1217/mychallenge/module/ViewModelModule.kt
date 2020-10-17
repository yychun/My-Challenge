package com.yychun1217.mychallenge.module

import com.yychun1217.mychallenge.datasource.IDeliveryDataSource
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {
    @Provides
    @ActivityScoped
    fun providerDeliveryListViewModel(
        remote: IDeliveryDataSource
    ) = DeliveryListViewModel(remote)
}