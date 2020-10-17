package com.yychun1217.pagination.datasource

interface IMergedPagingSource<KEY, DATA> {
    val local: ILocalPagingSource<KEY, DATA>
    val remote: IRemotePagingSource<KEY, DATA>
}