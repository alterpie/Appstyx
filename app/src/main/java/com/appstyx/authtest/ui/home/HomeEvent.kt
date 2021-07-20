package com.appstyx.authtest.ui.home

sealed interface HomeEvent {
    object LogoutSuccess : HomeEvent
}
