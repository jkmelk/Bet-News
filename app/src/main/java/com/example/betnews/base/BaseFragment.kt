package com.example.betnews.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.betnews.base.shared_data.view_model.SharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    val sharedViewModel: SharedViewModel by viewModel()

    protected abstract val layoutResId: Int

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutResId, container, false)

    open fun onBackPressed() = false
}