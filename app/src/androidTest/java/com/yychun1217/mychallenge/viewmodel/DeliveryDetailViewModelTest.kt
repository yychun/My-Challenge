package com.yychun1217.mychallenge.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mychallenge.BaseTestCase
import com.yychun1217.mychallenge.datasource.local.IDeliveryLocalRepository
import com.yychun1217.mychallenge.dummy.Dummy
import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.util.anyObject
import com.yychun1217.mychallenge.util.observeOnce
import com.yychun1217.pagination.model.EntityType
import kotlinx.coroutines.*

import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import kotlin.coroutines.CoroutineContext

@RunWith(AndroidJUnit4::class)
@SmallTest
class DeliveryDetailViewModelTest : BaseTestCase() {
    private lateinit var deliveryDetailViewModel: DeliveryDetailViewModel

    @Mock
    private lateinit var iDeliveryLocalRepository: IDeliveryLocalRepository

    @Before
    fun setup() {
        deliveryDetailViewModel = DeliveryDetailViewModel(iDeliveryLocalRepository)
    }

    @Test
    fun getDeliveryByID() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryLocalRepository.getDelivery(Dummy.DELIVER_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryByID(Dummy.DELIVER_ID)
            deliveryDetailViewModel.delivery.observeOnce {
                assert(it.id == Dummy.DELIVER_ID)
            }
        }
    }

    @Test
    fun toggleFavourite() {
        CoroutineScope(Dispatchers.Main).launch {
            Mockito.`when`(iDeliveryLocalRepository.getDelivery(Dummy.DELIVER_ID))
                .thenReturn(Dummy.DELIVERY_DB)
            deliveryDetailViewModel.getDeliveryByID(Dummy.DELIVER_ID)
            deliveryDetailViewModel.delivery.observeOnce {
                assert(it.id == Dummy.DELIVER_ID)

                val update = it.copy(isFavourite = !it.isFavourite)
                runBlocking {
                    (update.toEntity(EntityType.DB) as? IDeliveryContract.Db)?.let { updateDb ->
                        Mockito.`when`(iDeliveryLocalRepository.update(anyObject()))
                            .thenReturn(true)
                        deliveryDetailViewModel.toggleFavourite()
                        deliveryDetailViewModel.delivery.observeOnce {
                            assert(it.isFavourite == update.isFavourite)
                        }
                    }
                }
            }
        }
    }
}