package com.appstyx.authtest.ui.signup

import com.appstyx.authtest.domain.Gender

data class SignupState(
    val genders: List<Gender>,
    val selectedGender: Gender?,
) {
    companion object {
        val Initial = SignupState(emptyList(), null)
    }
}
