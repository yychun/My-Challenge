package com.yychun1217.pagination.pagingsource

import androidx.paging.PagingSource
import com.yychun1217.pagination.model.IEntityContract
import timber.log.Timber

abstract class AbstractMergedPagingSource<KEY : Any, DB : IEntityContract.Db<API, UI>, API : IEntityContract.Api<DB, UI>, UI : IEntityContract.Ui<API, DB>>(
    override val local: ILocalPagingSource<KEY, DB>,
    override val remote: IRemotePagingSource<KEY, API>
) : PagingSource<KEY, UI>(), IMergedPagingSource<KEY, DB, API> {
    override suspend fun load(params: LoadParams<KEY>): PagingSource.LoadResult<KEY, UI> {
        return try {
            val key = params.key ?: getFirstKey()
            val localPage = local.loadPage(key)
            if (localPage.isNotEmpty()) {
                mapToLoadResult(key, localPage, true)
            } else {
                val remotePage = remote.loadPage(key)
                val isRemotePaginationEndReached = remotePage.isEmpty()
                val page = if (remotePage.isNotEmpty()) {
                    local.insert(key, remotePage.mapNotNull { it.toDb() })
                } else emptyList()
                mapToLoadResult(key, page, !isRemotePaginationEndReached)
            }
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }

    private fun mapToLoadResult(
        key: KEY,
        page: List<DB>,
        isGetNextKey: Boolean
    ): LoadResult<KEY, UI> {
        return page.mapNotNull { it.toUi() }.let {
            LoadResult.Page(
                it,
                getPrevKey(key),
                if (isGetNextKey) getNextKey(key) else null
            )
        }
    }

    abstract fun getFirstKey(): KEY
    abstract fun getPrevKey(key: KEY): KEY?
    abstract fun getNextKey(key: KEY): KEY?
}