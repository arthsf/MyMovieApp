package com.example.mymovieapp.data.cloud.source

import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import kotlinx.coroutines.withContext
import retrofit2.Response

class ResponseHandlerImpl(
    private val dispatchersProvider: DispatchersProvider,
) : ResponseHandler {
    override suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T> {
        runCatching { withContext(dispatchersProvider.io()) { apiCall() } }.onSuccess { response ->
            if (response.isSuccessful) {
                val body =
                    withContext(dispatchersProvider.default()) { response.body() }
                body?.let {
                    return DataRequestState.Success(data = body)
                }
            }
        }
            .onFailure { exception -> return DataRequestState.Error(exception) }
        return DataRequestState.Error(
            error = java.lang.IllegalStateException())
    }

    override suspend fun <T, K> safeApiMapperCall(
        mapper: Mapper<T, K>,
        apiCall: suspend () -> Response<T>,
    ): DataRequestState<K> {
        runCatching { withContext(dispatchersProvider.io()) { apiCall() } }.onSuccess { response ->
            if (response.isSuccessful) {
                val body = withContext(dispatchersProvider.default()) { response.body() }
                body?.let {
                    return DataRequestState.Success(data = mapper.map(body))
                }
            }
        }
            .onFailure { exception -> return DataRequestState.Error(exception) }
        return DataRequestState.Error(
            error = java.lang.IllegalStateException())
    }
}