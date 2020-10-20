package com.yychun1217.mytask.datasource.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mytask.BaseTestCase
import com.yychun1217.mytask.dummy.Dummy
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
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.ROUTE_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDeliveryAndRoute(Dummy.ROUTE_ID)
        assert(delivery?.delivery?.id == Dummy.ROUTE_ID)
    }

    @Test
    fun testGetDeliveryByWrongIDReturnNull() = runBlocking {
        Mockito.`when`(iDeliveryAndRouteLocalDataSource.getDeliveryAndRoute(Dummy.ROUTE_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalRepository.getDeliveryAndRoute(Dummy.WRONG_ROUTE_ID)
        assert(delivery == null)
    }
}
