package com.appstyx.authtest.di

import com.appstyx.authtest.data.AuthRepositoryImpl
import com.appstyx.authtest.data.UserRepositoryImpl
import com.appstyx.authtest.data.api.auth.AuthApi
import com.appstyx.authtest.data.api.auth.AuthApiImpl
import com.appstyx.authtest.data.api.user.UserApi
import com.appstyx.authtest.data.api.user.UserApiImpl
import com.appstyx.authtest.data.storage.AuthStorage
import com.appstyx.authtest.data.storage.AuthStorageImpl
import com.appstyx.authtest.domain.auth.AuthRepository
import com.appstyx.authtest.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun userRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    fun authApi(impl: AuthApiImpl): AuthApi

    @Binds
    fun userApi(impl: UserApiImpl): UserApi

    @Singleton
    @Binds
    fun authStorage(impl: AuthStorageImpl): AuthStorage

}
