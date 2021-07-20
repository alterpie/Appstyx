package com.appstyx.authtest.ui.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.common.BaseViewModel
import com.appstyx.authtest.domain.auth.AuthRepository
import com.appstyx.authtest.domain.ValidationError
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<SignupState>(SignupState.Initial) {

    companion object {
        private const val TAG = "Signup"
    }

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
            viewModelScope.launch {
                runCatching {
                    authRepository.signup(
                        stateValue.email,
                        stateValue.firstName,
                        stateValue.lastName,
                        stateValue.selectedGender!!.id
                    )
                }
                    .onSuccess { sendEvent(SignupEvent.SignupSuccess) }
                    .onFailure { handleSignupError(it) }
            }
        } else {
            validationEvents.forEach(::sendEvent)
        }
    }

    fun loadGenders() {
        viewModelScope.launch {
            runCatching { authRepository.getGenders() }
                .onSuccess { updateState { copy(genders = it) } }
                .onFailure { Log.e(TAG, it.toString()) }
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

    private fun handleSignupError(throwable: Throwable) {
        if (throwable is ValidationError) {
            throwable.content.forEach { (key, message) ->
                InputKey.fromRawValue(key)?.let { sendEvent(SignupEvent.ApiError(it, message)) }
            }
        } else {
            Log.e(TAG, throwable.toString())
        }
    }
}