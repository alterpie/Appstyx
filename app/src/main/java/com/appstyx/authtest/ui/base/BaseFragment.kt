package com.appstyx.authtest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>:Fragment() {

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun initLifecycle(binding: VB):LifecycleObserver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = createBinding(inflater, container)
        viewLifecycleOwner.lifecycle.addObserver(initLifecycle(binding))
        return binding.root
    }
}
