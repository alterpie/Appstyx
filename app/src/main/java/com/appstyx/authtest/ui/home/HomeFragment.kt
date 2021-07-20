package com.appstyx.authtest.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.appstyx.authtest.databinding.FragmentHomeBinding
import com.appstyx.authtest.di.AppInjector
import com.appstyx.authtest.ui.base.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    internal lateinit var vmProvider: Provider<HomeViewModel>
    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return vmProvider.get() as T
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.getAppComponent().inject(this)
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initLifecycle(binding: FragmentHomeBinding): LifecycleObserver {
        return object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                initActionsListeners(binding)
                viewModel.state
                    .onEach { render(it, binding) }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun render(state: HomeState, binding: FragmentHomeBinding) = with(binding) {
        imageViewAvatar.load(state.avatarUrl)
        textViewFirstName.text = state.firstName
        textViewLastName.text = state.lastName
    }

    private fun initActionsListeners(binding: FragmentHomeBinding) = with(binding) {
        logoutButton.setOnClickListener { viewModel.onLogoutClick() }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
