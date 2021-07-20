package com.appstyx.authtest.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : Any>(initialState: State) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(initialState)

    val events: Flow<Any> get() = _events
    private val _events = MutableSharedFlow<Any>(replay = 0)

    protected val stateValue: State get() = _state.value

    protected fun updateState(updateBlock: State.() -> State) {
        _state.value = updateBlock(_state.value)
    }

    protected fun sendEvent(event: Any) {
        _events.tryEmit(event)
    }
}
