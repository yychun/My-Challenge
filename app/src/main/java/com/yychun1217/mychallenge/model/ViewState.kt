package com.yychun1217.mychallenge.model

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

sealed class ViewState {
    data class Loading(
        val isEmpty: Boolean
    ) : ViewState()

    data class Error(
        val error: Throwable,
        val isEmpty: Boolean
    ) : ViewState()

    object NotLoading : ViewState()
}

fun CombinedLoadStates.toViewState(): ViewState {
    val loadStates = arrayOf(
        this.refresh,
        this.prepend,
        this.append,
    )
    val errorState = loadStates.find { it.isError() } as? LoadState.Error
    val loadingState = loadStates.find { it.isLoading() } as? LoadState.Loading
    return when {
        errorState != null -> ViewState.Error(errorState.error, errorState == refresh)
        loadingState != null -> ViewState.Loading(loadingState == refresh)
        else -> ViewState.NotLoading
    }
}

fun LoadState.isError(): Boolean = this is LoadState.Error
fun LoadState.isLoading(): Boolean = this is LoadState.Loading