package com.yychun1217.pagination.datasource

import com.yychun1217.pagination.model.IEntity

interface ILocalPagingSource<KEY, DB: IEntity.Db> : IPagingSource<KEY, DB> {
    suspend fun insert(key: KEY, page: List<DB>): Boolean
}