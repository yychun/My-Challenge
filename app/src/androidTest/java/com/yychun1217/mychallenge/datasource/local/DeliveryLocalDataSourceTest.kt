package com.yychun1217.mychallenge.datasource.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yychun1217.mychallenge.BaseTestCase
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.dummy.Dummy
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
    private lateinit var deliveryDao: DeliveryDao

    @Before
    fun setup() {
        deliverLocalDataSource = DeliveryAndRouteLocalDataSource(deliveryDao)
    }

    @Test
    fun testGetDeliveryByIDReturnDelivery() = runBlocking {
        Mockito.`when`(deliveryDao.get(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalDataSource.getDeliveryAndRoute(Dummy.DELIVER_ID)
        assert(delivery?.id == Dummy.DELIVER_ID)
    }

    @Test
    fun testGetDeliveryByWrongIDReturnNull() = runBlocking {
        Mockito.`when`(deliveryDao.get(Dummy.DELIVER_ID)).thenReturn(Dummy.DELIVERY_DB)
        val delivery = deliverLocalDataSource.getDeliveryAndRoute(Dummy.DELIVER_WRONG_ID)
        assert(delivery == null)
    }

    @Test
    fun testUpdate() = runBlocking {
        Mockito.`when`(deliveryDao.update(Dummy.DELIVERY_DB_UPDATED)).thenReturn(Unit)
        val isSuccess = deliverLocalDataSource.update(Dummy.DELIVERY_DB_UPDATED)
        assert(isSuccess)
    }
}
