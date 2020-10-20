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
    private lateinit var deliverLocalRepository: DeliveryAndRouteLocalRepository
    @Mock
    private lateinit var iDeliveryAndRouteLocalDataSource: IDeliveryAndRouteLocalDataSource

    @Before
    fun setup() {
        deliverLocalRepository = DeliveryAndRouteLocalRepository(iDeliveryAndRouteLocalDataSource)
    }

    @Test
    fun testGetDeliveryByIDReturnDelivery() = runBlocking {
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDeliveryAndRoute(Dummy.DELIVER_ID)
        assert(delivery?.id == Dummy.DELIVER_ID)
    }

    @Test
    fun testGetDeliveryByWrongIDReturnNull() = runBlocking {
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDeliveryAndRoute(Dummy.DELIVER_WRONG_ID)
        assert(delivery == null)
    }

    @Test
    fun testUpdateSuccessful() = runBlocking {
        val returnIsSuccess = true
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.update(Dummy.DELIVERY_DB_UPDATED)).thenReturn(returnIsSuccess)
        val isSuccess = deliverLocalRepository.update(Dummy.DELIVERY_DB_UPDATED)
        assert(isSuccess == returnIsSuccess)
    }

    @Test
    fun testUpdateUnsuccessful() = runBlocking {
        val returnIsSuccess = false
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.update(Dummy.DELIVERY_DB_UPDATED)).thenReturn(returnIsSuccess)
        val isSuccess = deliverLocalRepository.update(Dummy.DELIVERY_DB_UPDATED)
        assert(isSuccess == returnIsSuccess)
    }
}
