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
    override suspend fun load(params: LoadParams<KEY>): PagingSource.LoadResult<KEY, UI> {
        val key = params.key ?: getFirstKey()
        val page = local.loadPage(key) ?: remote.loadPage(key)?.apply {
            local.insert(key, this.mapNotNull { it.toEntity(EntityType.DB) })
        }
        return page?.mapNotNull { it.toEntity(EntityType.UI) as? UI }.let {
            PagingSource.LoadResult.Page(it ?: emptyList(), getPrevKey(key), getNextKey(key))
        }
    }

    abstract fun getFirstKey(): KEY
    abstract fun getPrevKey(key: KEY): KEY?
    abstract fun getNextKey(key: KEY): KEY?
}