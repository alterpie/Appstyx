package com.appstyx.authtest.ui.main

sealed interface MainEvent {
    data class Navigate(val destination: MainViewModel.Destination) : MainEvent
}
