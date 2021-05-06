package me.tsalikis.blog

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios


internal actual val httpClient: HttpClient = HttpClient(Ios)