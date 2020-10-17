package com.yychun1217.pagination.pagingsource

import androidx.paging.PagingSource
import com.yychun1217.pagination.datasource.ILocalPagingSource
import com.yychun1217.pagination.datasource.IMergedPagingSource
import com.yychun1217.pagination.datasource.IRemotePagingSource

abstract class AbstractMergedPagingSource<KEY : Any, DATA : Any>(
    override val local: ILocalPagingSource<KEY, DATA>,
    override val remote: IRemotePagingSource<KEY, DATA>
) : PagingSource<KEY, DATA>(), IMergedPagingSource<KEY, DATA> {
    override suspend fun load(params: LoadParams<KEY>): LoadResult<KEY, DATA> {
        val key = params.key ?: getFirstKey()
        val page = local.loadPage(key) ?: remote.loadPage(key)?.apply {
            local.insert(key, this)
        }
        return page.let {
            LoadResult.Page(it ?: emptyList(), getPrevKey(key), getNextKey(key))
        }
    }

    abstract fun getFirstKey(): KEY
    abstract fun getPrevKey(key: KEY): KEY?
    abstract fun getNextKey(key: KEY): KEY?
}