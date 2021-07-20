package com.appstyx.authtest.di

object AppInjector {

    private lateinit var appComponent: AppComponent

    fun init() {
        appComponent = DaggerAppComponent.factory().create()
    }

    fun getAppComponent(): AppComponent = appComponent
}
