package com.appstyx.authtest.di

import android.content.Context

object AppInjector {

    private lateinit var appComponent: AppComponent

    fun init(context: Context) {
        appComponent = DaggerAppComponent.factory().create(context)
    }

    fun getAppComponent(): AppComponent = appComponent
}
