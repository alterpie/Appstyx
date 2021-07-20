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

    @OptIn(ExperimentalStdlibApi::class)
    fun onSignupClick() {
        val validationEvents = buildList {
            if (stateValue.email.isBlank()) {
                add(SignupEvent.EmailEmpty)
            }
            if (stateValue.firstName.isBlank()) {
                add(SignupEvent.FirstNameEmpty)
            }
            if (stateValue.lastName.isBlank()) {
                add(SignupEvent.LastNameEmpty)
            }
            if (stateValue.selectedGender == null) {
                add(SignupEvent.GenderEmpty)
            }
        }
        if (validationEvents.isEmpty()) {
            // signup call
        } else {
            validationEvents.forEach(::sendEvent)
        }
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

    fun onEmailChanged(input: String) {
        updateState { copy(email = input) }
    }

    fun onFirstNameChanged(input: String) {
        updateState { copy(firstName = input) }
    }

    fun onLastNameChanged(input: String) {
        updateState { copy(lastName = input) }
    }
}