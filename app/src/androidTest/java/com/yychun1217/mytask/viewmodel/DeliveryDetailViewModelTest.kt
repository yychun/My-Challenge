package com.yychun1217.mytask.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mytask.BaseTestCase
import com.yychun1217.mytask.datasource.local.IDeliveryAndRouteLocalRepository
import com.yychun1217.mytask.dummy.Dummy
import com.yychun1217.mytask.model.IDeliveryContract
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
    private lateinit var iDeliveryLocalRepository: IDeliveryAndRouteLocalRepository

    @Before
    fun setup() {
        deliveryDetailViewModel = DeliveryDetailViewModel(iDeliveryLocalRepository)
    }

    @Test
    fun getDeliveryByID() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryLocalRepository.getDeliveryAndRoute(Dummy.DELIVER_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryAndRouteByRouteID(Dummy.DELIVER_ID)
            deliveryDetailViewModel.deliveryAndRoute.observeOnce {
                assert(it.id == Dummy.DELIVER_ID)
            }
        }
    }

    @Test
    fun toggleFavourite() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryLocalRepository.getDeliveryAndRoute(Dummy.DELIVER_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryAndRouteByRouteID(Dummy.DELIVER_ID)
            deliveryDetailViewModel.deliveryAndRoute.observeOnce {
                assert(it.id == Dummy.DELIVER_ID)

                val update = it.copy(isFavourite = !it.isFavourite)
                runBlocking {
                    (update.toEntity(EntityType.DB) as? IDeliveryContract.Db)?.let { updateDb ->
                        Mockito.`when`(iDeliveryLocalRepository.update(anyObject()))
                            .thenReturn(true)
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