package com.yychun1217.pagination.pagingsource

import com.yychun1217.pagination.model.IEntityContract

interface ILocalPagingSource<KEY, DB: IEntityContract.Db> : IPagingSource<KEY, DB> {
    /*
    return a list of data successfully inserted into DB
     */
    suspend fun insert(key: KEY, page: List<DB>): List<DB>
}