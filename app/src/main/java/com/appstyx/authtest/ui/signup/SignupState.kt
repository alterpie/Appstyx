package com.appstyx.authtest.ui.signup

import com.appstyx.authtest.domain.auth.Gender

data class SignupState(
    val genders: List<Gender>,
    val selectedGender: Gender?,
    val email: String,
    val firstName: String,
    val lastName: String,
) {
    companion object {
        val Initial = SignupState(emptyList(), null, "", "", "")
    }
}
