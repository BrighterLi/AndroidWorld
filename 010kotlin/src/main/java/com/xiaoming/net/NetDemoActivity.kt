package com.xiaoming.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.xiaoming.a010kotlin.R

class NetDemoActivity : AppCompatActivity() {

    companion object {
        const val TAG = "NetDemoActivityPage"
    }
    lateinit var networkCallback: ConnectivityManager.NetworkCallback

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_demo)
        registerNetworkChangeCallbacks()
    }

    override fun onDestroy() {
        unregisterNetworkChangeCallbacks()
        super.onDestroy()
    }

    fun initNetworkCallbacks() {
        Log.d(TAG, "network#initNetworkCallbacks")
        networkCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d(TAG, "network#initNetworkCallbacks:onAvailable")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d(TAG, "network#initNetworkCallbacks:onLost")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerNetworkChangeCallbacks() {
        Log.d(TAG, "network#registerNetworkChangeCallbacks")
        if(!this::networkCallback.isInitialized) {
            initNetworkCallbacks()
        }
        val connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        Log.d(TAG, "network#registerNetworkChangeCallbacks: Looper.myLooper:${ Looper.myLooper()}")
        Log.d(TAG, "network#registerNetworkChangeCallbacks: Looper.getMainLooper:${ Looper.getMainLooper()}")
        Looper.myLooper()?.let {
            Log.d(TAG, "network#registerNetworkChangeCallbacks:registerDefaultNetworkCallback")
            connectivityManager.registerDefaultNetworkCallback(networkCallback, Handler(it))
            Log.d(TAG, "network#registerNetworkChangeCallbacks:registerDefaultNetworkCallback2")
        }
    }

    fun unregisterNetworkChangeCallbacks() {
        Log.d(TAG, "network#unregisterNetworkChangeCallbacks")
        if(!this::networkCallback.isInitialized) {
            val connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            Looper.myLooper()?.let {
                connectivityManager.unregisterNetworkCallback(networkCallback)
                Log.d(TAG, "network#unregisterNetworkChangeCallbacks:unregisterNetworkCallback")
            }
        }
    }

}
