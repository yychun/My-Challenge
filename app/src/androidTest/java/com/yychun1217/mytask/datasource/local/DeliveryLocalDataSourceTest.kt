package com.yychun1217.mytask.datasource.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mytask.BaseTestCase
import com.yychun1217.mytask.db.DeliveryAndRouteDao
import com.yychun1217.mytask.dummy.Dummy
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
@SmallTest
class DeliveryLocalDataSourceTest : BaseTestCase() {

    private lateinit var deliverLocalDataSource: DeliveryAndRouteLocalDataSource

    @Mock
    private lateinit var deliveryAndRouteDao: DeliveryAndRouteDao

    @Before
    fun setup() {
        deliverLocalDataSource = DeliveryAndRouteLocalDataSource(deliveryAndRouteDao)
    }

    @Test
    fun testGetDeliveryByIDReturnDelivery() = runBlocking {
        Mockito.`when`(deliveryAndRouteDao.get(Dummy.ROUTE_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalDataSource.getDeliveryAndRoute(Dummy.ROUTE_ID)
        assert(delivery?.route?.id == Dummy.ROUTE_ID)
    }

    @Test
    fun testGetDeliveryByWrongIDReturnNull() = runBlocking {
        Mockito.`when`(deliveryAndRouteDao.get(Dummy.ROUTE_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalDataSource.getDeliveryAndRoute(Dummy.WRONG_ROUTE_ID)
        assert(delivery == null)
    }
}
