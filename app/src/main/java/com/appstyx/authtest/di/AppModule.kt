package com.appstyx.authtest.di

import com.appstyx.authtest.data.AuthRepositoryImpl
import com.appstyx.authtest.data.api.AuthApi
import com.appstyx.authtest.data.api.AuthApiImpl
import com.appstyx.authtest.data.storage.AuthStorage
import com.appstyx.authtest.data.storage.AuthStorageImpl
import com.appstyx.authtest.domain.AuthRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun authApi(impl: AuthApiImpl): AuthApi

    @Singleton
    @Binds
    fun authStorage(impl: AuthStorageImpl): AuthStorage

}
