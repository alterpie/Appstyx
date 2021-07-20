package com.appstyx.authtest.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.common.BaseViewModel
import com.appstyx.authtest.domain.auth.AuthRepository
import com.appstyx.authtest.domain.user.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel<HomeState>(HomeState.Initial) {

    companion object {
        private const val TAG = "Home"
    }

    init {
        viewModelScope.launch {
            runCatching { userRepository.getUser() }
                .onSuccess {
                    updateState {
                        copy(
                            firstName = it.firstName,
                            lastName = it.lastName,
                            avatarUrl = it.avatarUrl
                        )
                    }
                }
                .onFailure { Log.e(TAG, it.toString()) }
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            runCatching { authRepository.logout() }
                .onSuccess { sendEvent(HomeEvent.LogoutSuccess) }
                .onFailure { Log.e(TAG, it.toString()) }
        }
    }
}