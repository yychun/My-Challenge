package com.yychun1217.pagination.datasource

import com.yychun1217.pagination.model.IEntity

interface IMergedPagingSource<KEY, DB : IEntity.Db, API : IEntity.Api> {
    val local: ILocalPagingSource<KEY, DB>
    val remote: IRemotePagingSource<KEY, API>
}