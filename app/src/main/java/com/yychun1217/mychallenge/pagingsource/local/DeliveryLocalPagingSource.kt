package com.yychun1217.mychallenge.pagingsource.local

import android.database.sqlite.SQLiteConstraintException
import com.yychun1217.mychallenge.db.DeliveryDao
import com.yychun1217.mychallenge.model.IDeliveryContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import timber.log.Timber

class DeliveryLocalPagingSource(
    private val deliveryDao: DeliveryDao
) : ILocalPagingSource<GetDeliveryRequest, IDeliveryContract.Db> {
    override suspend fun insert(
        key: GetDeliveryRequest,
        page: List<IDeliveryContract.Db>
    ): List<IDeliveryContract.Db> {
        return try {
            deliveryDao.insertAll(*page.toTypedArray())
            page
        } catch (e: SQLiteConstraintException) {
            Timber.e(e)
            page.filter {
                try {
                    deliveryDao.insert(it)
                    true
                } catch (e: SQLiteConstraintException) {
                    Timber.e(e)
                    false
                }
            }
        }
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryContract.Db> =
        deliveryDao.getAll(key.offset, key.limit)
}