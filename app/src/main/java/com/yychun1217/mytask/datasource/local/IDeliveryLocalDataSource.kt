package com.yychun1217.mytask.datasource.local

import com.yychun1217.mytask.model.IDeliveryContract

interface IDeliveryLocalDataSource : ILocalDataSource<IDeliveryContract.Db> {
    suspend fun update(vararg delivery: IDeliveryContract.Db): Int
}