package com.yychun1217.mychallenge.di

import com.yychun1217.mychallenge.DeliveryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {
    companion object {
        private const val BASE_URL = "https://mock-api-mobile.dev.lalamove.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Inject
    @Singleton
    fun provideDeliveryService(
        retrofit: Retrofit
    ): DeliveryService = retrofit.create(DeliveryService::class.java)
}