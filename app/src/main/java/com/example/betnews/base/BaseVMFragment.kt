package com.example.betnews.base

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.koin.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseVMFragment<V : BaseViewModel> : BaseFragment() {
    lateinit var viewModel: V
        private set

    private fun createViewModel(): V {
        val clazz =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        return getKoin().getViewModel(this, clazz.kotlin as KClass<V>)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
    }
}