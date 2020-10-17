package com.yychun1217.pagination.datasource

import com.yychun1217.pagination.model.IEntity

interface IRemotePagingSource<KEY, API: IEntity.Api> : IPagingSource<KEY, API>