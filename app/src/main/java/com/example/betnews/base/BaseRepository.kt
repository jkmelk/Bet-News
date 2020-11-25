package com.example.betnews.base

import android.util.Log
import com.example.betnews.base.model.ErrorResponse
import com.example.betnews.base.model.ResultWrapper
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    protected suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                          apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError()
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.string()?.let {
                Gson().fromJson(it, ErrorResponse::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }
}