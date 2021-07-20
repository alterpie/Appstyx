package com.appstyx.authtest.ui.signup

sealed interface SignupEvent {
    object EmailEmpty : SignupEvent
    object FirstNameEmpty : SignupEvent
    object LastNameEmpty : SignupEvent
    object GenderEmpty : SignupEvent
}
