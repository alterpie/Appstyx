package com.appstyx.authtest.ui.main

import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.common.BaseViewModel
import com.appstyx.authtest.domain.auth.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<MainState>(MainState) {

    enum class Destination {
        Signup, Home
    }

    init {
        viewModelScope.launch {
            val destination = if (authRepository.isAuthorised()) {
                Destination.Home
            } else {
                Destination.Signup
            }
            sendEvent(MainEvent.Navigate(destination))
        }
    }

    fun navigate(destination: Destination){
        sendEvent(MainEvent.Navigate(destination))
    }
}
