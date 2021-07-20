package com.appstyx.authtest.ui.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.common.BaseViewModel
import com.appstyx.authtest.domain.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<SignupState>(SignupState.Initial) {

    fun onSignupClick() {
        // TODO
    }

    fun loadGenders() {
        viewModelScope.launch {
            runCatching { authRepository.getGenders() }
                .onSuccess { updateState { copy(genders = it) } }
                .onFailure { Log.d("Signup", it.toString()) }
        }
    }

    fun selectGender(position: Int) {
        stateValue.genders.getOrNull(position)
            ?.let { updateState { copy(selectedGender = it) } }
    }
}