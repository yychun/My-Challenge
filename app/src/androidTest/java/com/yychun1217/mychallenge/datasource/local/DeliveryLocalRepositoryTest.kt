package com.yychun1217.mychallenge.datasource.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mychallenge.BaseTestCase
import com.yychun1217.mychallenge.dummy.Dummy
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@SmallTest
class DeliveryLocalRepositoryTest : BaseTestCase() {
    private lateinit var deliverLocalRepository: DeliveryLocalRepository
    @Mock
    private lateinit var iDeliveryLocalDataSource: IDeliveryLocalDataSource

    @Before
    fun setup() {
        deliverLocalRepository = DeliveryLocalRepository(iDeliveryLocalDataSource)
    }

    @Test
    fun testGetDeliveryByIDReturnDelivery() = runBlocking {
        Mockito.`when`(iDeliveryLocalDataSource.getDelivery(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDelivery(Dummy.DELIVER_ID)
        assert(delivery?.id == Dummy.DELIVER_ID)
    }

    @Test
    fun testGetDeliveryByWrongIDReturnNull() = runBlocking {
        Mockito.`when`(iDeliveryLocalDataSource.getDelivery(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDelivery(Dummy.DELIVER_WRONG_ID)
        assert(delivery == null)
    }

    @Test
    fun testUpdateSuccessful() = runBlocking {
        val returnIsSuccess = true
        Mockito.`when`(iDeliveryLocalDataSource.update(Dummy.DELIVERY_DB_UPDATED)).thenReturn(returnIsSuccess)
        val isSuccess = deliverLocalRepository.update(Dummy.DELIVERY_DB_UPDATED)
        assert(isSuccess == returnIsSuccess)
    }

    @Test
    fun testUpdateUnsuccessful() = runBlocking {
        val returnIsSuccess = false
        Mockito.`when`(iDeliveryLocalDataSource.update(Dummy.DELIVERY_DB_UPDATED)).thenReturn(returnIsSuccess)
        val isSuccess = deliverLocalRepository.update(Dummy.DELIVERY_DB_UPDATED)
        assert(isSuccess == returnIsSuccess)
    }
}
