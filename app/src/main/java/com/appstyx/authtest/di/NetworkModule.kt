package com.appstyx.authtest.di

import com.appstyx.authtest.BuildConfig
import com.appstyx.authtest.data.api.auth.model.ApiErrorResponse
import com.appstyx.authtest.data.storage.AuthStorage
import com.appstyx.authtest.domain.ValidationError
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun httpClient(serializationJson: Json): HttpClient {
        return HttpClient(CIO) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.HOST
                    encodedPath = "${BuildConfig.BASE_PATH}$encodedPath"
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(serializationJson)
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            HttpResponseValidator {
                handleResponseException { throwable ->
                    if (throwable is ClientRequestException) {
                        val apiError =
                            serializationJson.decodeFromString<ApiErrorResponse>(throwable.response.readText())
                        if (apiError.message == "Validation Error") {
                            throw ValidationError(apiError.errors!!.map { it.param to it.msg })
                        }
                    }
                }
            }
        }
    }

    @Provides
    @Singleton
    fun json(): Json {
        return Json { ignoreUnknownKeys = true }
    }
}
