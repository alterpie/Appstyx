package com.appstyx.authtest.ui.signup

sealed interface SignupEvent {
    object EmailEmpty : SignupEvent
    object FirstNameEmpty : SignupEvent
    object LastNameEmpty : SignupEvent
    object GenderEmpty : SignupEvent
    object SignupSuccess : SignupEvent
    data class ApiError(val key: InputKey, val message: String) : SignupEvent
}

sealed interface InputKey {

    companion object {
        fun fromRawValue(value: String): InputKey? {
            return when (value) {
                "email" -> Email
                "firstName" -> FirstName
                "lastName" -> LastName
                else -> null
            }
        }
    }

    object Email : InputKey
    object FirstName : InputKey
    object LastName : InputKey


}
