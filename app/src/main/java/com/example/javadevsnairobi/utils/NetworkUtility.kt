package com.example.javadevsnairobi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtility {
    fun isConnected(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var connected = false

        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            connected = networkInfo != null && networkInfo.isConnectedOrConnecting
        }

        return connected
    }
}
