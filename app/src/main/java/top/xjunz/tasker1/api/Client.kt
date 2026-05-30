/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

/**
 * @author Mengran 2023/03/01
 */
class Client {

    private val host = "http://no-such-host:886"

    private val httpClient: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        engine {
            requestTimeout = 5000
        }
    }

    suspend fun getCurrentPrice(): HttpResponse {
        return withContext(Dispatchers.IO) {
            httpClient.post("$host/p/c")
        }
    }

    suspend fun createOrder(deviceId: String): HttpResponse {
        return withContext(Dispatchers.IO) {
            httpClient.post("$host/o/c") {
                encryptHeaders(deviceId)
            }
        }
    }

    suspend fun checkOderState(orderId: String): HttpResponse {
        return withContext(Dispatchers.IO) {
            httpClient.post("$host/o/r") {
                encryptHeaders(orderId)
            }
        }
    }

    suspend fun restorePurchase(uid: String, deviceId: String): HttpResponse {
        return withContext(Dispatchers.IO) {
            httpClient.post("$host/u/c") {
                encryptHeaders(uid, deviceId)
            }
        }
    }

    suspend fun consumeRedeem(code: String, deviceId: String): HttpResponse {
        return withContext(Dispatchers.IO) {
            httpClient.post("$host/g/c") {
                encryptHeaders(code, deviceId)
            }
        }
    }

    suspend fun checkForUpdates(): UpdateInfo? {
        return withContext(Dispatchers.IO) {
            runCatching {
                val response = httpClient.get("https://api.github.com/repos/liuyi-6699/AutoTask/releases/latest")
                if (response.status != HttpStatusCode.OK) {
                    return@withContext null
                }
                val root = Json.parseToJsonElement(response.bodyAsText()).jsonObject
                val assets = root["assets"]!!.jsonArray[0].jsonObject
                val tagName = root["tag_name"]!!.jsonPrimitive.content
                val version = tagName.removePrefix("v")
                val body = root["body"]?.jsonPrimitive?.content.orEmpty()
                val downloadUrl = assets["browser_download_url"]!!.jsonPrimitive.content
                val size = assets["size"]!!.jsonPrimitive.long
                val htmlUrl = root["html_url"]!!.jsonPrimitive.content
                val name = root["name"]!!.jsonPrimitive.content

                UpdateInfo(
                    binary = UpdateInfo.Binary(size),
                    build = root["tag_name"]?.jsonPrimitive?.content.orEmpty(),
                    changelog = body,
                    direct_install_url = downloadUrl,
                    installUrl = downloadUrl,
                    install_url = downloadUrl,
                    name = name,
                    update_url = htmlUrl,
                    updated_at = 0L,
                    version = version,
                    versionShort = version
                )
            }.getOrNull()
        }
    }

    fun close() {
        httpClient.close()
    }
}