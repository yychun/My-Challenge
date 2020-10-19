package com.yychun1217.mychallenge.pagingsource.local

import android.database.sqlite.SQLiteConstraintException
import com.yychun1217.mychallenge.db.MiscDao
import com.yychun1217.mychallenge.model.IDeliveryAndRouteContract
import com.yychun1217.mychallenge.model.request.GetDeliveryRequest
import com.yychun1217.pagination.pagingsource.ILocalPagingSource
import timber.log.Timber

class DeliveryLocalPagingSource(
    private val miscDao: MiscDao
) : ILocalPagingSource<GetDeliveryRequest, IDeliveryAndRouteContract.Db> {
    override suspend fun insert(
        key: GetDeliveryRequest,
        page: List<IDeliveryAndRouteContract.Db>
    ): List<IDeliveryAndRouteContract.Db> {
        return try {
            miscDao.insert(*page.toTypedArray())
            page
        } catch (e: SQLiteConstraintException) {
            Timber.e(e)
            page.filter {
                try {
                    miscDao.insert(it)
                    true
                } catch (e: SQLiteConstraintException) {
                    Timber.e(e)
                    false
                }
            }
        }
    }

    override suspend fun loadPage(key: GetDeliveryRequest): List<IDeliveryAndRouteContract.Db> = miscDao.getAll(key.offset, key.limit)
}