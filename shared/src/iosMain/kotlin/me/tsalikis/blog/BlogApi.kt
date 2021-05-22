package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json


internal actual val httpClient: HttpClient = HttpClient(Ios) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
        })
    }
}