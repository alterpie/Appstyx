package com.appstyx.authtest.di

import android.content.Context
import com.appstyx.authtest.ui.home.HomeFragment
import com.appstyx.authtest.ui.main.MainActivity
import com.appstyx.authtest.ui.signup.SignupFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(target: SignupFragment)
    fun inject(target: MainActivity)
    fun inject(target: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
