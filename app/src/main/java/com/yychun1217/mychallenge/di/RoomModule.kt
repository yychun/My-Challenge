package com.yychun1217.mychallenge.di

import android.content.Context
import androidx.room.Room
import com.yychun1217.mychallenge.db.MyDatabase
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.db.MiscDao
import com.yychun1217.mychallenge.db.RouteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Provides
    @Singleton
    @Inject
    fun provideAppDatabase(
        @ApplicationContext
        applicationContext: Context
    ): MyDatabase =
        Room.databaseBuilder(applicationContext, MyDatabase::class.java, MyDatabase.NAME).build()

    @Provides
    @Singleton
    @Inject
    fun provideDeliveryDao(
        myDatabase: MyDatabase
    ): DeliveryDao = myDatabase.deliveryDao()

    @Provides
    @Singleton
    @Inject
    fun provideRouteDao(
        myDatabase: MyDatabase
    ): RouteDao = myDatabase.routeDao()

    @Provides
    @Singleton
    @Inject
    fun provideMiscDao(
        myDatabase: MyDatabase
    ): MiscDao = myDatabase.miscDao()
}