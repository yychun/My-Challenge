package com.yychun1217.pagination.pagingsource

import com.yychun1217.pagination.model.IEntityContract

interface IMergedPagingSource<KEY, DB : IEntityContract.Db, API : IEntityContract.Api> {
    val local: ILocalPagingSource<KEY, DB>
    val remote: IRemotePagingSource<KEY, API>
}