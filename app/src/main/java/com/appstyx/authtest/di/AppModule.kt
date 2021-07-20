package com.appstyx.authtest.di

import com.appstyx.authtest.data.AuthApi
import com.appstyx.authtest.data.AuthApiImpl
import com.appstyx.authtest.data.AuthRepositoryImpl
import com.appstyx.authtest.domain.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun authApi(impl: AuthApiImpl): AuthApi

}
