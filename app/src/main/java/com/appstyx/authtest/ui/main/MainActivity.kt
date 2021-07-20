package com.appstyx.authtest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appstyx.authtest.R
import com.appstyx.authtest.di.AppInjector
import com.appstyx.authtest.ui.home.HomeFragment
import com.appstyx.authtest.ui.main.MainViewModel.Destination.Home
import com.appstyx.authtest.ui.main.MainViewModel.Destination.Signup
import com.appstyx.authtest.ui.signup.SignupFragment
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var vmProvider: Provider<MainViewModel>
    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return vmProvider.get() as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppInjector.getAppComponent().inject(this)
        setContentView(R.layout.activity_main)

        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        val lifecycleOwner: LifecycleOwner = this
        with(viewModel) {
            changeDestinationEvent.observe(lifecycleOwner) { destination ->
                val fragment = when (destination) {
                    Signup -> SignupFragment.newInstance()
                    Home -> HomeFragment.newInstance()
                    else -> throw IllegalArgumentException("Destination not supported")
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
        }
    }
}