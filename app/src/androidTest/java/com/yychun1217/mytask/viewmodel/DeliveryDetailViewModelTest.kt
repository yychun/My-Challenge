package com.yychun1217.mytask.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mytask.BaseTestCase
import com.yychun1217.mytask.datasource.local.IDeliveryAndRouteLocalDataSource
import com.yychun1217.mytask.datasource.local.IDeliveryLocalDataSource
import com.yychun1217.mytask.dummy.Dummy
import com.yychun1217.mytask.util.anyObject
import com.yychun1217.mytask.util.observeOnce
import kotlinx.coroutines.*

import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@SmallTest
class DeliveryDetailViewModelTest : BaseTestCase() {
    private lateinit var deliveryDetailViewModel: DeliveryDetailViewModel

    @Mock
    private lateinit var iDeliveryLocalDataSource: IDeliveryLocalDataSource

    @Mock
    private lateinit var iDeliveryAndRouteLocalDataSource: IDeliveryAndRouteLocalDataSource

    @Before
    fun setup() {
        deliveryDetailViewModel =
            DeliveryDetailViewModel(iDeliveryLocalDataSource, iDeliveryAndRouteLocalDataSource)
    }

    @Test
    fun getDeliveryByID() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.ROUTE_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryAndRouteByRouteID(Dummy.ROUTE_ID)
            deliveryDetailViewModel.deliveryAndRoute.observeOnce {
                assert(it.route._idDb == Dummy.ROUTE_ID)
            }
        }
    }

    @Test
    fun toggleFavourite() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.ROUTE_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryAndRouteByRouteID(Dummy.ROUTE_ID)
            deliveryDetailViewModel.deliveryAndRoute.observeOnce {
                assert(it.route._idDb == Dummy.ROUTE_ID)

                val update = it.copy(
                    delivery = it.delivery.copy(
                        isFavourite = !it.delivery.isFavourite
                    )
                )
                runBlocking {
                    update.toDb()?.let {
                        Mockito.`when`(iDeliveryLocalDataSource.update(anyObject()))
                            .thenReturn(1)
                        deliveryDetailViewModel.toggleFavourite()
                        deliveryDetailViewModel.deliveryAndRoute.observeOnce {
                            assert(it.isFavourite == update.isFavourite)
                        }
                    }
                }
            }
        }
    }
}