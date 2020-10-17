package com.yychun1217.pagination.pagingsource

import androidx.paging.PagingSource
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IMergedPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource
import com.yychun1217.pagination.model.EntityType
import com.yychun1217.pagination.model.IEntity

abstract class AbstractMergedPagingSource<KEY : Any, DB : IEntity.Db, API : IEntity.Api, UI : IEntity.Ui>(
    override val local: ILocalPagingSource<KEY, DB>,
    override val remote: IRemotePagingSource<KEY, API>
) : PagingSource<KEY, UI>(), IMergedPagingSource<KEY, DB, API> {
    override suspend fun load(params: LoadParams<KEY>): LoadResult<KEY, UI> {
        return try {
            val key = params.key ?: getFirstKey()
            val page = local.loadPage(key) ?: remote.loadPage(key)?.apply {
                local.insert(key, this.mapNotNull { it.toEntity(EntityType.DB) })
            }
            page?.mapNotNull { it.toEntity(EntityType.UI) as? UI }.orEmpty().let {
                LoadResult.Page(it, getPrevKey(key, it.size), getNextKey(key, it.size))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract fun getFirstKey(): KEY
    abstract fun getPrevKey(key: KEY, pageSize: Int): KEY?
    abstract fun getNextKey(key: KEY, pageSize: Int): KEY?
}