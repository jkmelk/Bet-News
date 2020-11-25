package com.example.betnews.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betnews.base.model.ResultWrapper
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected suspend fun <T : Any> manageCall(
        resultWrapper: ResultWrapper<T>,
        errorCall: suspend (ResultWrapper.GenericError) -> Unit = { },
        call: suspend (T) -> Unit = {}
    ) {
        when (resultWrapper) {
            is ResultWrapper.Success -> {
                call.invoke(resultWrapper.value)
            }
            is ResultWrapper.NetworkError -> {
//                handleNetworkError(resultWrapper)
            }
            is ResultWrapper.GenericError -> {
                handleGenericError(resultWrapper)
                errorCall.invoke(resultWrapper)
            }
        }
    }

    protected fun handleGenericError(resultWrapper: ResultWrapper.GenericError) {
        resultWrapper.error?.error?.params?.let {
            if (it.isNotEmpty()) {
            }
        }
    }
}