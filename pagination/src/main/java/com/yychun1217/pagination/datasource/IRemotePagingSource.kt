package com.yychun1217.pagination.datasource

import com.yychun1217.pagination.model.IEntityContract

interface IRemotePagingSource<KEY, API: IEntityContract.Api> : IPagingSource<KEY, API>