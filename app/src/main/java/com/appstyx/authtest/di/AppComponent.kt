package com.appstyx.authtest.di

import com.appstyx.authtest.data.AuthApi
import com.appstyx.authtest.data.AuthApiImpl
import com.appstyx.authtest.data.AuthRepositoryImpl
import com.appstyx.authtest.domain.AuthRepository
import com.appstyx.authtest.ui.signup.SignupFragment
import dagger.Binds
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(target: SignupFragment)

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
