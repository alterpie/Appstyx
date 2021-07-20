package com.appstyx.authtest.di

import com.appstyx.authtest.BuildConfig
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun httpClient(): HttpClient {
        return HttpClient(CIO) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.HOST
                    encodedPath = "${BuildConfig.BASE_PATH}$encodedPath"
                }
            }
            install(JsonFeature) {
                val json = Json {
                    ignoreUnknownKeys = true
                }

                serializer = KotlinxSerializer(json)
            }
        }
    }
}
