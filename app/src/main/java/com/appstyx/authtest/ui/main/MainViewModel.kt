package com.appstyx.authtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.common.Event
import com.appstyx.authtest.domain.auth.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    enum class Destination {
        Signup, Home
    }

    val changeDestinationEvent = Event<Destination>()

    init {
        viewModelScope.launch {
            val destination = if (authRepository.isAuthorised()) {
                Destination.Home
            } else {
                Destination.Signup
            }
            changeDestinationEvent.value = destination
        }

    }
}
