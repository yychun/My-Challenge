package com.yychun1217.mytask.util

import androidx.lifecycle.LiveData
import com.yychun1217.mytask.observer.OneTimeObserver
import org.mockito.Mockito

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

fun <T> anyObject(): T {
    return Mockito.anyObject<T>()
}