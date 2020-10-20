package com.yychun1217.mychallenge.datasource.local

import com.yychun1217.mychallenge.model.IDeliveryContract

interface IDeliveryLocalDataSource : ILocalDataSource<IDeliveryContract.Db> {
    suspend fun update(vararg delivery: IDeliveryContract.Db): Int
}