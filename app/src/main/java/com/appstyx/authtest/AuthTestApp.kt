package com.appstyx.authtest

import android.app.Application
import com.appstyx.authtest.di.AppInjector

class AuthTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
