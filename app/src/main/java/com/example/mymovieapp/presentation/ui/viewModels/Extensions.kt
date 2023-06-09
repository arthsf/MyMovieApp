package com.example.mymovieapp.presentation.ui.viewModels

import android.content.Context
import android.widget.Toast
import com.example.mymovieapp.presentation.models.ResponseState
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow


fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
    MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)

fun changeResponseState(page: Int, totalPage: Int): ResponseState =
    ResponseState(
        totalPage = totalPage,
        page = page,
        isHasNextPage = page < totalPage,
        isHasPreviousPage = page > 1)

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}