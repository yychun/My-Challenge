package com.yychun1217.pagination.datasource

interface IPagingSource<KEY, DATA> {
    suspend fun loadPage(key: KEY): List<DATA>
}