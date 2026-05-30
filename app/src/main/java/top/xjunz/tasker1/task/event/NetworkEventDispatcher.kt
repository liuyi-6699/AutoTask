/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.event

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import top.xjunz.tasker1.bridge.ConnectivityManagerBridge
import top.xjunz.tasker1.bridge.ContextBridge
import top.xjunz.tasker1.engine.runtime.Event
import top.xjunz.tasker1.engine.task.EventDispatcher

/**
 * @author Mengran 2023/10/13
 */
class NetworkEventDispatcher : EventDispatcher() {

    companion object {
        const val EXTRA_WIFI_SSID = 0
    }

    private var currentWifiSSID: String? = null

    private var isNetworkAvailable = false

    private val cm by lazy {
        ContextBridge.getContext().getSystemService(ConnectivityManager::class.java)
    }

    private val networkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (!isNetworkAvailable) {
                dispatchEvents(Event.obtain(Event.EVENT_ON_NETWORK_AVAILABLE))
            }
            isNetworkAvailable = true
        }

        override fun onUnavailable() {
            super.onUnavailable()
            if (isNetworkAvailable) {
                dispatchEvents(Event.obtain(Event.EVENT_ON_NETWORK_UNAVAILABLE))
            }
            isNetworkAvailable = false
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {

            val prevSsid = currentWifiSSID
            val ssid = if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                ConnectivityManagerBridge.getCurrentConnectedWifiSSID()
            } else {
                null
            }
            currentWifiSSID = ssid
            var event: Event? = null
            if (ssid != prevSsid) {
                if (ssid != null) {
                    event = Event.obtain(Event.EVENT_ON_WIFI_CONNECTED)
                    event.putExtra(EXTRA_WIFI_SSID, ssid)
                } else if (prevSsid != null) {
                    event = Event.obtain(Event.EVENT_ON_WIFI_DISCONNECTED)
                    event.putExtra(EXTRA_WIFI_SSID, prevSsid)
                }
            }
            if (event != null) {
                dispatchEvents(event)
            }
        }
    }

    override fun destroy() {
        cm.unregisterNetworkCallback(networkCallback)
    }

    override fun onRegistered() {
        isNetworkAvailable = cm.activeNetwork != null
        currentWifiSSID = ConnectivityManagerBridge.getCurrentConnectedWifiSSID()
        cm.registerDefaultNetworkCallback(networkCallback)
    }
}