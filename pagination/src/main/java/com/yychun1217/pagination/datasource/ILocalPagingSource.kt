package com.yychun1217.pagination.datasource

interface ILocalPagingSource<KEY, DATA> : IPagingSource<KEY, DATA> {
    suspend fun insert(key: KEY, page: List<DATA>): Boolean
}