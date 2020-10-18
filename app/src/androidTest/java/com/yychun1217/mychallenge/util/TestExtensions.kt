package com.yychun1217.mychallenge.util

import androidx.lifecycle.LiveData
import com.yychun1217.mychallenge.observer.OneTimeObserver
import org.mockito.Mockito

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

fun <T> anyObject(): T {
    return Mockito.anyObject<T>()
}