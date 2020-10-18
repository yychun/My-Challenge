package com.yychun1217.pagination.pagingsource

interface IPagingSource<KEY, DATA> {
    suspend fun loadPage(key: KEY): List<DATA>
}